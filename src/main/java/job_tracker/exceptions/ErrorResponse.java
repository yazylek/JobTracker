package job_tracker.exceptions;

public record ErrorResponse(
        int status,
        String message
) {
}
