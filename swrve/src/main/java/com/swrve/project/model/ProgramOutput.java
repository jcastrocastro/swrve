package com.swrve.project.model;

public class ProgramOutput {
    private long totalCountUsers;
    // 640x960 (width x height)
    private long numberUsersWithStandardResolution;
    private long totalSpend;
    private String firstUserId;

    public long getTotalCountUsers() {
        return totalCountUsers;
    }

    public void setTotalCountUsers(long totalCountUsers) {
        this.totalCountUsers = totalCountUsers;
    }

    public long getNumberUsersWithStandardResolution() {
        return numberUsersWithStandardResolution;
    }

    public void setNumberUsersWithStandardResolution(long numberUsersWithStandardResolution) {
        this.numberUsersWithStandardResolution = numberUsersWithStandardResolution;
    }

    public long getTotalSpend() {
        return totalSpend;
    }

    public void setTotalSpend(long totalSpend) {
        this.totalSpend = totalSpend;
    }

    public String getFirstUserId() {
        return firstUserId;
    }

    public void setFirstUserId(String firstUserId) {
        this.firstUserId = firstUserId;
    }

    @Override
    public String toString() {
        String builder = ProgramOutput.class.getSimpleName()
            + " {totalCountUsers="
            + totalCountUsers
            + " {numberUsersStandardResolution="
            + numberUsersWithStandardResolution
            + " {totalSpend="
            + totalSpend
            + " {firstUserId="
            + firstUserId
            + "}";
        return builder;
    }
}
