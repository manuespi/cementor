package es.ucm.fdi.iw.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An authorized user of the system.
 */
@Entity
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "User.byUsername", query = "SELECT u FROM User u "
                + "WHERE u.username = :username AND u.enabled = TRUE"),
        @NamedQuery(name = "User.hasUsername", query = "SELECT COUNT(u) "
                + "FROM User u "
                + "WHERE u.username = :username")
})
@Table(name = "IWUser")
@Data
@Getter
public class User implements Transferable<User.Transfer> {

    public enum Role {
        // ALUMNO,
        // MENTOR,
        USER, // normal users
        ADMIN
        // admin users
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "gen")
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "SCORE")
    private double score;

    @OneToMany
    @JoinColumn(name = "COMMENT_ID")
    private List<Comment> comments;

    @OneToMany
    @JoinColumn(name = "MENTORING_ID")
    private List<Mentoring> mentorings;

    @OneToMany
    @JoinColumn(name = "REVIEW_ID")
    private List<Review> review;

    private boolean enabled;
    private String roles; // split by ',' to separate roles

    @OneToMany
    @JoinColumn(name = "sender_id")
    private List<Message> sent = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "recipient_id")
    private List<Message> received = new ArrayList<>();

    /**
     * Checks whether this user has a given role.
     * 
     * @param role to check
     * @return true iff this user has that role.
     */
    public boolean hasRole(Role role) {
        String roleName = role.name();
        return Arrays.asList(roles.split(",")).contains(roleName);
    }

    public String getRoles() {
        return roles;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Getter
    @AllArgsConstructor
    public static class Transfer {
        private long id;
        private String username;
        private int totalReceived;
        private int totalSent;
    }

    @Override
    public Transfer toTransfer() {
        return null;
        // return new Transfer(id, username, received.size(), sent.size());
    }

    @Override
    public String toString() {
        return toTransfer().toString();
    }
}
