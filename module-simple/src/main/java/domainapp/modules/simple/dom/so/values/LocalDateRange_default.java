package domainapp.modules.simple.dom.so.values;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.apache.causeway.applib.annotation.Action;
import org.apache.causeway.applib.annotation.ActionLayout;
import org.apache.causeway.applib.annotation.MemberSupport;
import org.apache.causeway.applib.annotation.PromptStyle;
import org.apache.causeway.applib.annotation.SemanticsOf;

@Action(semantics = SemanticsOf.SAFE)
@ActionLayout(promptStyle = PromptStyle.INLINE_AS_IF_EDIT)
@RequiredArgsConstructor
public class LocalDateRange_default {
    private final LocalDateRange mixee;

    @MemberSupport
    public LocalDateRange act(
            LocalDate startDate,
            Boolean unbefristet,
            LocalDate endDate
    ) {
        final var endDate1 = unbefristet ? LocalDateRange.UNBEFRISTET_MAX_DATE : endDate;
        return LocalDateRange.of(startDate, endDate1);
    }

    @MemberSupport
    public Boolean defaultUnbefristet() {
        return LocalDateRange.UNBEFRISTET_MAX_DATE.equals(mixee.getEndDate());
    }

    @MemberSupport
    public LocalDate defaultStartDate() {
        return mixee.getStartDate();
    }

    @MemberSupport
    public LocalDate defaultEndDate() {
        return mixee.getEndDate();
    }

}
