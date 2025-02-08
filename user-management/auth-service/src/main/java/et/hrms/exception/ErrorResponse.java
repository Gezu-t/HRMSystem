package et.hrms.exception;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor@NoArgsConstructor
public class ErrorResponse {
    private String error;
    private String message;
}