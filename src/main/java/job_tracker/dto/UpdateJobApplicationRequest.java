package job_tracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import job_tracker.ApplicationStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateJobApplicationRequest(
        @NotBlank String companyName,
        @NotBlank String position,
        @PositiveOrZero BigDecimal salaryRangeMin,
        @PositiveOrZero BigDecimal salaryRangeMax,
        @NotNull LocalDate applicationDate,
        @NotNull ApplicationStatus status
) {
}
