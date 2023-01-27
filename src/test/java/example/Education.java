
package example;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class Education {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("school")
    @Expose
    private String school;
    @SerializedName("degree")
    @Expose
    private String degree;
    @SerializedName("study")
    @Expose
    private String study;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("fromdate")
    @Expose
    private String fromdate;
    @SerializedName("todate")
    @Expose
    private String todate;
    @SerializedName("date")
    @Expose
    private String date;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Education() {
    }

    /**
     * 
     * @param date
     * @param study
     * @param todate
     * @param school
     * @param degree
     * @param description
     * @param id
     * @param fromdate
     */
    public Education(Integer id, String school, String degree, String study, String description, String fromdate, String todate, String date) {
        super();
        this.id = id;
        this.school = school;
        this.degree = degree;
        this.study = study;
        this.description = description;
        this.fromdate = fromdate;
        this.todate = todate;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getStudy() {
        return study;
    }

    public void setStudy(String study) {
        this.study = study;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Education.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("school");
        sb.append('=');
        sb.append(((this.school == null)?"<null>":this.school));
        sb.append(',');
        sb.append("degree");
        sb.append('=');
        sb.append(((this.degree == null)?"<null>":this.degree));
        sb.append(',');
        sb.append("study");
        sb.append('=');
        sb.append(((this.study == null)?"<null>":this.study));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
        sb.append(',');
        sb.append("fromdate");
        sb.append('=');
        sb.append(((this.fromdate == null)?"<null>":this.fromdate));
        sb.append(',');
        sb.append("todate");
        sb.append('=');
        sb.append(((this.todate == null)?"<null>":this.todate));
        sb.append(',');
        sb.append("date");
        sb.append('=');
        sb.append(((this.date == null)?"<null>":this.date));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
