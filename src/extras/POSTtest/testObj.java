package extras.POSTtest;

public class testObj{
    private Long id =1L;
    private Long idNo=1L;
    private String deptName="中文测试";
    private String deptMan="中文测试";
    private String rPlace="中文测试";
    private String rType="中文测试";
    private String repairType="中文测试";
    private String deviceName="中文测试";
    private String rContent="中文测试";
    private String linkMan="中文测试";
    private String mobilePhone="中文测试";
    private String linkWayMobile="中文测试";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdNo() {
        return idNo;
    }

    public void setIdNo(Long idNo) {
        this.idNo = idNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptMan() {
        return deptMan;
    }

    public void setDeptMan(String deptMan) {
        this.deptMan = deptMan;
    }

    public String getrPlace() {
        return rPlace;
    }

    public void setrPlace(String rPlace) {
        this.rPlace = rPlace;
    }

    public String getrType() {
        return rType;
    }

    public void setrType(String rType) {
        this.rType = rType;
    }

    public String getRepairType() {
        return repairType;
    }

    public void setRepairType(String repairType) {
        this.repairType = repairType;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getrContent() {
        return rContent;
    }

    public void setrContent(String rContent) {
        this.rContent = rContent;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getLinkWayMobile() {
        return linkWayMobile;
    }

    public void setLinkWayMobile(String linkWayMobile) {
        this.linkWayMobile = linkWayMobile;
    }

    @Override
    public String toString() {
        return "testObj{" +
                "id=" + id +
                ", idNo=" + idNo +
                ", deptName='" + deptName + '\'' +
                ", deptMan='" + deptMan + '\'' +
                ", rPlace='" + rPlace + '\'' +
                ", rType='" + rType + '\'' +
                ", repairType='" + repairType + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", rContent='" + rContent + '\'' +
                ", linkMan='" + linkMan + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", linkWayMobile='" + linkWayMobile + '\'' +
                '}';
    }

    public testObj(Long id, Long idNo, String repairType, String deviceName) {
        this.id = id;
        this.idNo = idNo;
        this.repairType = repairType;
        this.deviceName = deviceName;
    }
}
