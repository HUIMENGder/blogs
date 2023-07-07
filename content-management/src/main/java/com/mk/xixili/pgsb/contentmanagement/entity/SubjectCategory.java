package com.mk.xixili.pgsb.contentmanagement.entity;

import java.util.List;

public enum SubjectCategory {
    /**
     * PHILOSOPHY [note: '哲学']
     *     ECONOMICS [note: '经济学']
     *     LAW [note: '法学']
     *     EDUCATION [note: '教育学']
     *     LITERATURE [note: '文学']
     *     HISTORY [note: '历史学']
     *     SCIENCE [note: '理学']
     *     ENGINEERING [note: '工学']
     *     AGRICULTURE [note: '农学']
     *     MEDICINE [note: '医学']
     *     MANAGEMENT [note: '管理学']
     *     ART [note: '艺术学']
     *     CROSS [note: '交叉学科']
     *     PE [note: '体育学']
     */

    /**
     * 哲学
     */
    PHILOSOPHY("哲学"),

    /**
     * 经济学
     */
    ECONOMICS("经济学"),

    /**
     * 法学
     */
    LAW("法学"),

    /**
     * 教育学
     */
    EDUCATION("教育学"),

    /**
     * 文学
     */
    LITERATURE("文学"),

    /**
     * 历史学
     */
    HISTORY("历史学"),

    /**
     * 理学
     */
    SCIENCE("理学"),

    /**
     * 工学
     */
    ENGINEERING("工学"),

    /**
     * 农学
     */
    AGRICULTURE("农学"),

    /**
     * 医学
     */
    MEDICINE("医学"),

    /**
     * 管理学
     */
    MANAGEMENT("管理学"),

    /**
     * 艺术学
     */
    ART("艺术学"),

    /**
     * 交叉学科
     */
    CROSS("交叉学科"),

    /**
     * 体育学
     */
    PE("体育学"),
    ;

    private String displayName;

    SubjectCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static List<SubjectCategory> getAll() {
        return List.of(SubjectCategory.values());
    }
}
