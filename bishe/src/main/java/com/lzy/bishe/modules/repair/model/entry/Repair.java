package com.lzy.bishe.modules.repair.model.entry;

import lombok.Data;

@Data
public class Repair {
    /* 报修人 */
    public Integer repairId;
    public Integer repairUserId;
    public String repairUsername;
    public Integer communityId;
    public String repairContent;
    public String repairPicture;
    public String homeId;
    public String repairPhone;
    public String repairEmail;
    public String repairTime;
    /* 维修人 */
    public Integer okRepairUserId;
    public String okRepairUsername;
    public String okRepairTime;
    /* 报修状态 */
    public String repairStatus;

}
