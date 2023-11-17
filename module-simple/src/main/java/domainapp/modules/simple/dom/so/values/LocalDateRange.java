package domainapp.modules.simple.dom.so.values;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.apache.causeway.applib.annotation.MemberSupport;
import org.apache.causeway.applib.annotation.Value;

@Value
@lombok.Value
@lombok.AllArgsConstructor(staticName = "of")
public class LocalDateRange {
    LocalDate startDate;
    LocalDate endDate;

    public static final LocalDate UNBEFRISTET_MAX_DATE = LocalDate.of(9999, 12, 31);
    public static final DateTimeFormatter germanDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public boolean isUnbefristet() {
        return UNBEFRISTET_MAX_DATE.equals(endDate);
    }

    @MemberSupport
    public String title() {
        if (startDate == null) {
            return "n.d.";
        }
        final var start = startDate.format(germanDateFormatter);

        if (isUnbefristet()) {
            return start + " - unbef.";
        }
        final var end = endDate.format(germanDateFormatter);
        return start + " - " + end;
    }
}
