package BLL;

import DAL.ChartDAL;

public class ChartBLL {
    public static double[] getRevenueForMonth(int month, int year, int numDays) {
        return ChartDAL.getRevenueForMonth(month, year, numDays);
    }

    public static double[] getRevenueForYearByMonth(int year) {
        return ChartDAL.getRevenueForYearByMonth(year);
    }

    public static double[] getRevenueForYears(int startYear, int numYears) {
        return ChartDAL.getRevenueForYears(startYear, numYears);
    }

    public static int[] countOrdersForMonth(int month, int year, int numDays) {
        return ChartDAL.countOrdersForMonth(month, year, numDays);
    }

    public static int[] countOrdersForYearByMonth(int year) {
        return ChartDAL.countOrdersForYearByMonth(year);
    }

    public static int[] countOrdersForYears(int startYear, int numYears) {
        return ChartDAL.countOrdersForYears(startYear, numYears);
    }

    public static double[] getCostForMonth(int month, int year, int numDays) {
        return ChartDAL.getCostForMonth(month, year, numDays);
    }

    public static double[] getCostForYearByMonth(int year) {
        return ChartDAL.getCostForYearByMonth(year);
    }

    public static double[] getCostForYears(int startYear, int numYears) {
        return ChartDAL.getCostForYears(startYear, numYears);
    }
}