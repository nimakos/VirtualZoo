package gr.nikolis.handlers.exceptions.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse{
    private Date timeStamp;
    private String message;
    private String details;
}
