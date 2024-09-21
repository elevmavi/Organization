import java.util.ArrayList;
import java.util.List;

class Member {
    private String name;
    private String email;
    private String phone;
    private int age;
    private List<Subscription> subscriptions;

    public Member(String name, String email, String phone, int age) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.subscriptions = new ArrayList<>();
    }

    public void addSubscription(Subscription subscription) {
        subscriptions.add(subscription);
    }

    public String getName() {
        return name;
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    // άλλες μέθοδοι όπως getters και setters
}

class Subscription {
    private Department department;
    private int visitsAllowed;
    private int visitsRemaining;

    public Subscription(Department department, int visitsAllowed) {
        this.department = department;
        this.visitsAllowed = visitsAllowed;
        this.visitsRemaining = visitsAllowed;
    }

    public Department getDepartment() {
        return department;
    }

    public int getVisitsRemaining() {
        return visitsRemaining;
    }

    public void useVisit() {
        if (visitsRemaining > 0) {
            visitsRemaining--;
        } else {
            System.out.println("No visits remaining for this subscription.");
        }
    }

    public void resetVisits() {
        this.visitsRemaining = visitsAllowed;
    }
}

class Department {
    private String name;
    private List<Schedule> schedule;
    private final int maxParticipants = 6;

    public Department(String name) {
        this.name = name;
        this.schedule = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Schedule> getSchedule() {
        return schedule;
    }

    public void addSchedule(Schedule sch) {
        schedule.add(sch);
    }

    public boolean isAvailable(Schedule sch) {
        return sch.getParticipants().size() < maxParticipants;
    }
}

class Schedule {
    private String day;
    private String startTime;
    private String endTime;
    private List<Member> participants;

    public Schedule(String day, String startTime, String endTime) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.participants = new ArrayList<>();
    }

    public String getDay() {
        return day;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public List<Member> getParticipants() {
        return participants;
    }

    public void addParticipant(Member member) {
        if (participants.size() < 6) {
            participants.add(member);
        } else {
            System.out.println("No available slots for this time.");
        }
    }

    public void removeParticipant(Member member) {
        participants.remove(member);
    }
}

public class Main {
    public static void main(String[] args) {
        // Δημιουργία μελών
        Member member1 = new Member("John Doe", "john@example.com", "6900000000", 25);
        Member member2 = new Member("Jane Smith", "jane@example.com", "6900000001", 30);

        // Δημιουργία τμημάτων
        Department swimming = new Department("Swimming");
        Department crossfit = new Department("CrossFit");

        // Δημιουργία προγράμματος
        Schedule swimmingMonday = new Schedule("Monday", "09:00", "11:00");
        Schedule swimmingWednesday = new Schedule("Wednesday", "16:00", "17:00");
        swimming.addSchedule(swimmingMonday);
        swimming.addSchedule(swimmingWednesday);

        Schedule crossfitTuesday = new Schedule("Tuesday", "10:00", "12:00");
        crossfit.addSchedule(crossfitTuesday);

        // Δημιουργία συνδρομών για τα μέλη
        Subscription sub1 = new Subscription(swimming, 8);
        Subscription sub2 = new Subscription(crossfit, 15);

        // Προσθήκη συνδρομών στα μέλη
        member1.addSubscription(sub1);
        member2.addSubscription(sub2);

        // Εγγραφή μελών στο πρόγραμμα
        swimmingMonday.addParticipant(member1);
        swimmingWednesday.addParticipant(member1);
        crossfitTuesday.addParticipant(member2);

        // Έλεγχος για διαθεσιμότητα
        if (swimming.isAvailable(swimmingMonday)) {
            System.out.println("Υπάρχουν διαθέσιμες θέσεις για το Κολυμβητήριο τη Δευτέρα.");
        } else {
            System.out.println("Δεν υπάρχουν διαθέσιμες θέσεις για το Κολυμβητήριο τη Δευτέρα.");
        }
    }
}
