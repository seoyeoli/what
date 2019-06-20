package com.my.company.schedule.vo;

public class scheduleVO {
	
	//just_schedule column
	String sch_id; 
	String user_id; 
	String sch_day; 
	String sch_time; 
	String sch_wrt_user_id; 
	
	//just_prog column
	String prog_id; 
	String prog_type; 
	String prog_trainer; 
	String prog_remark;
	public String getSch_id() {
		return sch_id;
	}
	public void setSch_id(String sch_id) {
		this.sch_id = sch_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getSch_day() {
		return sch_day;
	}
	public void setSch_day(String sch_day) {
		this.sch_day = sch_day;
	}
	public String getSch_time() {
		return sch_time;
	}
	public void setSch_time(String sch_time) {
		this.sch_time = sch_time;
	}
	public String getSch_wrt_user_id() {
		return sch_wrt_user_id;
	}
	public void setSch_wrt_user_id(String sch_wrt_user_id) {
		this.sch_wrt_user_id = sch_wrt_user_id;
	}
	public String getProg_id() {
		return prog_id;
	}
	public void setProg_id(String prog_id) {
		this.prog_id = prog_id;
	}
	public String getProg_type() {
		return prog_type;
	}
	public void setProg_type(String prog_type) {
		this.prog_type = prog_type;
	}
	public String getProg_trainer() {
		return prog_trainer;
	}
	public void setProg_trainer(String prog_trainer) {
		this.prog_trainer = prog_trainer;
	}
	public String getProg_remark() {
		return prog_remark;
	}
	public void setProg_remark(String prog_remark) {
		this.prog_remark = prog_remark;
	}
	@Override
	public String toString() {
		return "scheduleVO [sch_id=" + sch_id + ", user_id=" + user_id + ", sch_day=" + sch_day + ", sch_time="
				+ sch_time + ", sch_wrt_user_id=" + sch_wrt_user_id + ", prog_id=" + prog_id + ", prog_type="
				+ prog_type + ", prog_trainer=" + prog_trainer + ", prog_remark=" + prog_remark + "]";
	}
	
	
	
}
