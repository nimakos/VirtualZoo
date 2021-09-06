package gr.nikolis.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MessageBean {
    private String message;

    @Override
    public String toString() {
        return String.format("[message=%s]", message);
    }
}
