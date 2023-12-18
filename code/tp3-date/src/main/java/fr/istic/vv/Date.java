package fr.istic.vv;

class Date implements Comparable<Date> {

    static class BadDateException extends Exception {
        public BadDateException(String s) {
            super(s);
        }
    }
    private int day;
    private int month;
    private int year;
    public Date(int day, int month, int year) throws Exception {
        if (!isValidDate(day,month,year)) {
            throw new BadDateException("La date n'est pas valide");
        } else {
            this.day = day;
            this.month = month;
            this.year = year;
        }
    }

    public static boolean isValidDate(int day, int month, int year) {
        // variables for lisibility
        boolean badYear = year <= 0;
        boolean badMonth = month < 0 || month > 12;
        boolean badDay = day < 0 || day > 31;
        if (badYear || badMonth || badDay) return false;
        System.out.println("Not that bad");
        boolean month30 = month == 4 || month == 6 || month == 9 || month == 11; // months which have 30 days
        if (month30 && day > 30) return false;
        System.out.println("No exceed in days");
        boolean badFebruaryDate = month == 2 && ((isLeapYear(year) && day > 29) || (!isLeapYear(year) && day > 28));
        if (badFebruaryDate) {
            System.out.println("Problem leap year");
            return false;
        } else {
            System.out.println("No problem");
            return true;
        }
    }

    public static boolean isLeapYear(int year) {
        // une année bissextile est divisible par 4 mais pas par 100 sauf si elle est aussi divisible par 400
        return year%4 == 0 && (year%100 !=0 || year%100 == 0 && year%400 == 0);
    }

    public Date nextDate() {
        int nextDay;
        int nextMonth;
        int nextYear;
        boolean month30 = month == 4 || month == 6 || month == 9 || month == 11; // months which have 30 day
        // Cas pour lequel on démarre un nouveau mois
        boolean leapYear = isLeapYear(year);
        boolean endOfMonth = (month30 && day == 30) || (month == 2 && ((leapYear && day == 29) || (leapYear && day == 28)));
        if (endOfMonth)  {
            nextDay = 1;
            nextMonth = this.month + 1;
            nextYear = year;
        } else {
            // Cas pour lequel on démarre une nouvelle année
            boolean endOfYear = month == 12 && day == 31;
            if (endOfYear) {
                nextDay = 1;
                nextMonth = 1;
                nextYear = year + 1;
            } else {
                nextDay = day + 1;
                nextMonth = month + 1;
                nextYear = year + 1;
            }
        }

        try {
            return new Date(nextDay, nextMonth, nextYear);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Date previousDate() { return null; }

    public int compareTo(Date other) { return 0; }
}