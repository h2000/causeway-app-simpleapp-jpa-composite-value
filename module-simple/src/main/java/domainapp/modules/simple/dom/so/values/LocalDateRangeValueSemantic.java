package domainapp.modules.simple.dom.so.values;

import java.time.LocalDate;
import org.apache.causeway.applib.util.schema.CommonDtoUtils;
import org.apache.causeway.applib.value.semantics.DefaultsProvider;
import org.apache.causeway.applib.value.semantics.Renderer;
import org.apache.causeway.applib.value.semantics.ValueDecomposition;
import org.apache.causeway.applib.value.semantics.ValueSemanticsAbstract;
import org.apache.causeway.schema.common.v2.ValueType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Component
@Import({ LocalDateRange_default.class})
public class LocalDateRangeValueSemantic extends ValueSemanticsAbstract<LocalDateRange> {

    @Override
    public Class<LocalDateRange> getCorrespondingClass() {
        return LocalDateRange.class;
    }

    @Override
    public ValueType getSchemaValueType() {
        return ValueType.COMPOSITE;
    }

    @Override
    public ValueDecomposition decompose(LocalDateRange value) {
        return CommonDtoUtils.typedTupleBuilder(value)
                .addFundamentalType(ValueType.LOCAL_DATE, "startDate", LocalDateRange::getStartDate)
                .addFundamentalType(ValueType.LOCAL_DATE, "endDate", LocalDateRange::getEndDate)
                .buildAsDecomposition();
    }

    @Override
    public LocalDateRange compose(ValueDecomposition decomposition) {
        return decomposition.right()
                .map(CommonDtoUtils::typedTupleAsMap)
                .map(map -> LocalDateRange.of(
                        (LocalDate) map.get("startDate"),
                        (LocalDate) map.get("endDate")))
                .orElse(null);
    }

    @Override
    public DefaultsProvider<LocalDateRange> getDefaultsProvider() {
        return () -> LocalDateRange.of(LocalDate.now().withDayOfMonth(1), LocalDateRange.UNBEFRISTET_MAX_DATE);
    }

    @Override
    public Renderer<LocalDateRange> getRenderer() {
        return new Renderer<>() {

            @Override
            public String titlePresentation(Context context, LocalDateRange value) {
                if (value == null) {
                    return "n.d.";
                }
                return value.title();
            }
        };
    }
}
