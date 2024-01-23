package it.unibo.nestedenum;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    private static final int SHORT_MONTH = 28;
    private static final int USUAL_MONTH = 30;
    private static final int LONG_MONTH = 31;

    public static enum Month {
        JANUARY(LONG_MONTH),
        FEBRUARY(SHORT_MONTH),
        MARCH(LONG_MONTH),
        APRIL(USUAL_MONTH),
        MAY(LONG_MONTH),
        JUNE(USUAL_MONTH),
        JULY(LONG_MONTH),
        AUGUST(LONG_MONTH),
        SEPTEMBER(USUAL_MONTH),
        OCTOBER(LONG_MONTH),
        NOVEMBER(USUAL_MONTH),
        DECEMBER(LONG_MONTH);

        private final int daysNumber;

        private Month(final int days) {
            this.daysNumber = days;
        }

        public int getDaysNumber() {
            return this.daysNumber;
        }

        public static Month fromString(final String str) {
            final ArrayList<Month> result = new ArrayList<>();
            final String upperStr = str.toUpperCase();
            for (final Month mese : Month.values()) {
                final String monthName = mese.name();
                if (monthName.contains(upperStr)) {
                    result.add(mese);
                }
            }
            switch (result.size()) {
                case 0:
                    throw new IllegalArgumentException("No month with such name");
                case 1:
                    return result.get(0);
                default:
                    throw new IllegalArgumentException("Ambiguos entry" + result.toString());
            }

        }
    }

    @Override
    public Comparator<String> sortByDays() {
        return new Comparator<String>() {
            public int compare(final String o1, final String o2) {
                return Month.fromString(o1).getDaysNumber() - Month.fromString(o2).getDaysNumber();
            }
        };
    }

    @Override
    public Comparator<String> sortByOrder() {
        return null;
    }
}
