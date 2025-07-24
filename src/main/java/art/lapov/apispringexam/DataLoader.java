package art.lapov.apispringexam;

import art.lapov.apispringexam.entity.*;
import art.lapov.apispringexam.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final EventUserRepository eventUserRepository;
    private final PaymentRepository paymentRepository;
    private final ExpenseRepository expenseRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataLoader(
            UserRepository userRepository,
            EventRepository eventRepository,
            EventUserRepository eventUserRepository,
            PaymentRepository paymentRepository,
            ExpenseRepository expenseRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.eventUserRepository = eventUserRepository;
        this.paymentRepository = paymentRepository;
        this.expenseRepository = expenseRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            loadMockData();
        }
    }

    private void loadMockData() {
        User alice = new User();
        alice.setName("Alice Johnson");
        alice.setEmail("alice@example.com");
        alice.setPassword(passwordEncoder.encode("1234"));

        User bob = new User();
        bob.setName("Bob Smith");
        bob.setEmail("bob@example.com");
        bob.setPassword(passwordEncoder.encode("1234"));

        User charlie = new User();
        charlie.setName("Charlie Brown");
        charlie.setEmail("charlie@example.com");
        charlie.setPassword(passwordEncoder.encode("1234"));

        User diana = new User();
        diana.setName("Diana Prince");
        diana.setEmail("diana@example.com");
        diana.setPassword(passwordEncoder.encode("1234"));

        User emily = new User();
        emily.setName("Emily Davis");
        emily.setEmail("emily@example.com");
        emily.setPassword(passwordEncoder.encode("1234"));

        alice = userRepository.save(alice);
        bob = userRepository.save(bob);
        charlie = userRepository.save(charlie);
        diana = userRepository.save(diana);
        emily = userRepository.save(emily);

        Event pizzaNight = new Event();
        pizzaNight.setDescription("Pizza Night");
        pizzaNight.setName("Pizza Night");
        pizzaNight.setCreatedAt(java.time.LocalDateTime.now().minusDays(7));

        Event movieTrip = new Event();
        movieTrip.setDescription("Movie Trip");
        movieTrip.setName("Movie Trip");
        movieTrip.setCreatedAt(java.time.LocalDateTime.now().minusDays(5));

        Event weekendGetaway = new Event();
        weekendGetaway.setDescription("Weekend Getaway");
        weekendGetaway.setName("Weekend Getaway");
        weekendGetaway.setCreatedAt(java.time.LocalDateTime.now().minusDays(3));

        pizzaNight = eventRepository.save(pizzaNight);
        movieTrip = eventRepository.save(movieTrip);
        weekendGetaway = eventRepository.save(weekendGetaway);

        EventUser pizzaNightOwner = new EventUser();
        pizzaNightOwner.setId(new EventUserId(pizzaNight.getId(), alice.getId()));
        pizzaNightOwner.setEvent(pizzaNight);
        pizzaNightOwner.setUser(alice);
        pizzaNightOwner.setRole(Role.OWNER);

        EventUser pizzaNightParticipant1 = new EventUser();
        pizzaNightParticipant1.setId(new EventUserId(pizzaNight.getId(), bob.getId()));
        pizzaNightParticipant1.setEvent(pizzaNight);
        pizzaNightParticipant1.setUser(bob);
        pizzaNightParticipant1.setRole(Role.PARTICIPANT);

        EventUser pizzaNightParticipant2 = new EventUser();
        pizzaNightParticipant2.setId(new EventUserId(pizzaNight.getId(), charlie.getId()));
        pizzaNightParticipant2.setEvent(pizzaNight);
        pizzaNightParticipant2.setUser(charlie);
        pizzaNightParticipant2.setRole(Role.PARTICIPANT);

        EventUser movieTripOwner = new EventUser();
        movieTripOwner.setId(new EventUserId(movieTrip.getId(), bob.getId()));
        movieTripOwner.setEvent(movieTrip);
        movieTripOwner.setUser(bob);
        movieTripOwner.setRole(Role.OWNER);

        EventUser movieTripParticipant1 = new EventUser();
        movieTripParticipant1.setId(new EventUserId(movieTrip.getId(), alice.getId()));
        movieTripParticipant1.setEvent(movieTrip);
        movieTripParticipant1.setUser(alice);
        movieTripParticipant1.setRole(Role.PARTICIPANT);

        EventUser movieTripParticipant2 = new EventUser();
        movieTripParticipant2.setId(new EventUserId(movieTrip.getId(), diana.getId()));
        movieTripParticipant2.setEvent(movieTrip);
        movieTripParticipant2.setUser(diana);
        movieTripParticipant2.setRole(Role.PARTICIPANT);

        EventUser movieTripParticipant3 = new EventUser();
        movieTripParticipant3.setId(new EventUserId(movieTrip.getId(), emily.getId()));
        movieTripParticipant3.setEvent(movieTrip);
        movieTripParticipant3.setUser(emily);
        movieTripParticipant3.setRole(Role.PARTICIPANT);

        EventUser weekendOwner = new EventUser();
        weekendOwner.setId(new EventUserId(weekendGetaway.getId(), diana.getId()));
        weekendOwner.setEvent(weekendGetaway);
        weekendOwner.setUser(diana);
        weekendOwner.setRole(Role.OWNER);

        EventUser weekendParticipant1 = new EventUser();
        weekendParticipant1.setId(new EventUserId(weekendGetaway.getId(), alice.getId()));
        weekendParticipant1.setEvent(weekendGetaway);
        weekendParticipant1.setUser(alice);
        weekendParticipant1.setRole(Role.PARTICIPANT);

        EventUser weekendParticipant2 = new EventUser();
        weekendParticipant2.setId(new EventUserId(weekendGetaway.getId(), charlie.getId()));
        weekendParticipant2.setEvent(weekendGetaway);
        weekendParticipant2.setUser(charlie);
        weekendParticipant2.setRole(Role.PARTICIPANT);

        eventUserRepository.save(pizzaNightOwner);
        eventUserRepository.save(pizzaNightParticipant1);
        eventUserRepository.save(pizzaNightParticipant2);
        eventUserRepository.save(movieTripOwner);
        eventUserRepository.save(movieTripParticipant1);
        eventUserRepository.save(movieTripParticipant2);
        eventUserRepository.save(movieTripParticipant3);
        eventUserRepository.save(weekendOwner);
        eventUserRepository.save(weekendParticipant1);
        eventUserRepository.save(weekendParticipant2);

        Expense pizzaCost = new Expense();
        pizzaCost.setDescription("Pizza and drinks");
        pizzaCost.setAmount(85.50);
        pizzaCost.setCreatedAt(java.time.LocalDateTime.now().minusDays(7));
        pizzaCost.setEvent(pizzaNight);
        pizzaCost.setUser(alice);

        Expense movieTickets = new Expense();
        movieTickets.setDescription("Movie tickets");
        movieTickets.setAmount(80.00);
        movieTickets.setCreatedAt(java.time.LocalDateTime.now().minusDays(5));
        movieTickets.setEvent(movieTrip);
        movieTickets.setUser(bob);

        Expense movieSnacks = new Expense();
        movieSnacks.setDescription("Popcorn and drinks");
        movieSnacks.setAmount(40.00);
        movieSnacks.setCreatedAt(java.time.LocalDateTime.now().minusDays(5));
        movieSnacks.setEvent(movieTrip);
        movieSnacks.setUser(diana);

        Expense accommodation = new Expense();
        accommodation.setDescription("Hotel accommodation");
        accommodation.setAmount(300.00);
        accommodation.setCreatedAt(java.time.LocalDateTime.now().minusDays(3));
        accommodation.setEvent(weekendGetaway);
        accommodation.setUser(diana);

        Expense gasoline = new Expense();
        gasoline.setDescription("Gas for the trip");
        gasoline.setAmount(75.00);
        gasoline.setCreatedAt(java.time.LocalDateTime.now().minusDays(3));
        gasoline.setEvent(weekendGetaway);
        gasoline.setUser(alice);

        Expense groceries = new Expense();
        groceries.setDescription("Groceries for weekend");
        groceries.setAmount(75.00);
        groceries.setCreatedAt(java.time.LocalDateTime.now().minusDays(3));
        groceries.setEvent(weekendGetaway);
        groceries.setUser(charlie);

        expenseRepository.save(pizzaCost);
        expenseRepository.save(movieTickets);
        expenseRepository.save(movieSnacks);
        expenseRepository.save(accommodation);
        expenseRepository.save(gasoline);
        expenseRepository.save(groceries);

        Payment bobPizzaPayment = new Payment();
        bobPizzaPayment.setAmount(28.50);
        bobPizzaPayment.setPaidAt(java.time.LocalDateTime.now().minusDays(6));
        bobPizzaPayment.setEvent(pizzaNight);
        bobPizzaPayment.setUser(bob);

        Payment charliePizzaPayment = new Payment();
        charliePizzaPayment.setAmount(28.50);
        charliePizzaPayment.setPaidAt(java.time.LocalDateTime.now().minusDays(6));
        charliePizzaPayment.setEvent(pizzaNight);
        charliePizzaPayment.setUser(charlie);

        Payment aliceMoviePayment = new Payment();
        aliceMoviePayment.setAmount(30.00);
        aliceMoviePayment.setPaidAt(java.time.LocalDateTime.now().minusDays(4));
        aliceMoviePayment.setEvent(movieTrip);
        aliceMoviePayment.setUser(alice);

        Payment emilyMoviePayment = new Payment();
        emilyMoviePayment.setAmount(30.00);
        emilyMoviePayment.setPaidAt(java.time.LocalDateTime.now().minusDays(4));
        emilyMoviePayment.setEvent(movieTrip);
        emilyMoviePayment.setUser(emily);

        Payment aliceWeekendPayment = new Payment();
        aliceWeekendPayment.setAmount(150.00);
        aliceWeekendPayment.setPaidAt(java.time.LocalDateTime.now().minusDays(2));
        aliceWeekendPayment.setEvent(weekendGetaway);
        aliceWeekendPayment.setUser(alice);

        paymentRepository.save(bobPizzaPayment);
        paymentRepository.save(charliePizzaPayment);
        paymentRepository.save(aliceMoviePayment);
        paymentRepository.save(emilyMoviePayment);
        paymentRepository.save(aliceWeekendPayment);
    }
}
