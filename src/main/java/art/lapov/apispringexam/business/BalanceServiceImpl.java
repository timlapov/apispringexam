package art.lapov.apispringexam.business;

import art.lapov.apispringexam.controller.dto.DebtDto;
import art.lapov.apispringexam.entity.Expense;
import art.lapov.apispringexam.entity.Payment;
import art.lapov.apispringexam.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BalanceServiceImpl implements BalanceService {

    @Override
    public List<DebtDto> calculate(List<User> participants, List<Expense> expenses, List<Payment> payments
    ) {
        if (participants == null || participants.isEmpty()) {
            return new ArrayList<>();
        }

        Map<String, String> userIdToEmailMap = participants.stream()
                .collect(Collectors.toMap(User::getId, User::getEmail));

        Map<String, Double> balance = new HashMap<>();
        participants.forEach(p -> balance.put(p.getId(), 0.0));

        double totalExpenses = expenses.stream().mapToDouble(Expense::getAmount).sum();
        double perPersonShare = totalExpenses / participants.size();

        for (Expense expense : expenses) {
            balance.put(expense.getUser().getId(), balance.get(expense.getUser().getId()) + expense.getAmount());
        }

        for (User participant : participants) {
            balance.put(participant.getId(), balance.get(participant.getId()) - perPersonShare);
        }

        for (Payment payment : payments) {
            balance.put(payment.getFromUser().getId(), balance.get(payment.getFromUser().getId()) + payment.getAmount());
            balance.put(payment.getToUser().getId(), balance.get(payment.getToUser().getId()) - payment.getAmount());
        }

        return settleDebts(balance, userIdToEmailMap);
    }

    private List<DebtDto> settleDebts(Map<String, Double> balance, Map<String, String> userIdToEmailMap) {
        Map<String, Double> debtors = new HashMap<>();
        Map<String, Double> creditors = new HashMap<>();

        for (Map.Entry<String, Double> entry : balance.entrySet()) {
            if (entry.getValue() < 0) {
                debtors.put(entry.getKey(), -entry.getValue());
            } else if (entry.getValue() > 0) {
                creditors.put(entry.getKey(), entry.getValue());
            }
        }

        List<DebtDto> debts = new ArrayList<>();

        for (Map.Entry<String, Double> debtor : debtors.entrySet()) {
            String debtorId = debtor.getKey();
            double debtorAmount = debtor.getValue();

            for (Map.Entry<String, Double> creditor : creditors.entrySet()) {
                String creditorId = creditor.getKey();
                double creditorAmount = creditor.getValue();

                if (debtorAmount > 0 && creditorAmount > 0) {
                    double payment = Math.min(debtorAmount, creditorAmount);

                    String fromUserEmail = userIdToEmailMap.get(debtorId);
                    String toUserEmail = userIdToEmailMap.get(creditorId);

                    debts.add(new DebtDto(fromUserEmail, toUserEmail, payment));

                    debtorAmount -= payment;
                    creditors.put(creditorId, creditorAmount - payment);

                    if (debtorAmount == 0) {
                        break;
                    }
                }
            }
        }
        return debts;
    }

}
