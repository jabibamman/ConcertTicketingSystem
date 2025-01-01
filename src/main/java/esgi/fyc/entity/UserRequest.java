package esgi.fyc.entity;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class UserRequest implements Comparable<UserRequest> {
    private User user;
    private int priority;  
    private LocalDateTime requestTime;

    @Override
    public int compareTo(UserRequest other) {
        if (this.priority != other.priority) 
            return Integer.compare(other.priority, this.priority);
        
        return this.requestTime.compareTo(other.requestTime);
    }
}