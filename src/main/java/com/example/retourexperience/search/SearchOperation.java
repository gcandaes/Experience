package com.example.retourexperience.search;

public enum SearchOperation {

    CONTAINS, DOES_NOT_CONTAIN, EQUAL, NOT_EQUAL, BEGINS_WITH,
    DOES_NOT_BEGIN_WITH, ENDS_WITH, DOES_NOT_END_WITH,
    NUL, NOT_NULL, GREATER_THAN, GREATER_THAN_EQUAL, LESS_THAN,
    LESS_THAN_EQUAL, ANY, ALL,
    PLACE_NAME_EQ, PLACE_ZIP_CODE_BW, PLACE_ZIP_CODE_EQ, WORK_START_DATE_GE, WORK_END_DATE_LE, OCCUPIED_FUNCTIONS;
    public static final String[] SIMPLE_OPERATION_SET = {
            "cn", "nc", "eq", "ne", "bw", "bn", "ew",
            "en", "nu", "nn", "gt", "ge", "lt", "le",
            "placeName"};

    public static SearchOperation getDataOption(final String dataOption) {
        switch (dataOption) {
            case "all":
                return ALL;
            case "any":
                return ANY;
            default:
                return null;
        }
    }

    public static SearchOperation getSimpleOperation(final String input) {
        switch (input) {
            case "placeNameEQ":
                return PLACE_NAME_EQ;
            case "placeZipCodeBW":
                return PLACE_ZIP_CODE_BW;
            case "placeZipCodeEQ":
                return PLACE_ZIP_CODE_EQ;
            case "workStartDateGE":
                return WORK_START_DATE_GE;
            case "workEndDateLE":
                return WORK_END_DATE_LE;
            case "occupiedFunctions":
                    return OCCUPIED_FUNCTIONS;
            case "cn":
                return CONTAINS;
            case "nc":
                return DOES_NOT_CONTAIN;
            case "eq":
                return EQUAL;
            case "ne":
                return NOT_EQUAL;
            case "bw":
                return BEGINS_WITH;
            case "bn":
                return DOES_NOT_BEGIN_WITH;
            case "ew":
                return ENDS_WITH;
            case "en":
                return DOES_NOT_END_WITH;
            case "nu":
                return NUL;
            case "nn":
                return NOT_NULL;
            case "gt":
                return GREATER_THAN;
            case "ge":
                return GREATER_THAN_EQUAL;
            case "lt":
                return LESS_THAN;
            case "le":
                return LESS_THAN_EQUAL;

            default:
                return null;
        }
    }
}