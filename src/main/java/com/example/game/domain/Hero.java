package com.example.game.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
@Entity
public class Hero {
	@Id
	@GeneratedValue(generator = "system_uuid")
	@GenericGenerator(name = "system_uuid", strategy = "uuid")
	private String ids;
	@Column
	private String identify;
	@Column
	private String name;
	// 0蜀国1吴国2魏国3群雄4神将
	@Column
	private Integer nation;
	@Column
	private String painting;
	// 0标准包 1SR标准包 2天罡包3地煞包4人杰包5特别包6破军包7魂烈包8阴阳包9贪狼包10三英包11将星包
	@Column(name = "package")
	private Integer _package;

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getIdentify() {
		return identify;
	}

	public void setIdentify(String identify) {
		this.identify = identify;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNation() {
		return nation;
	}

	public String getNationStr() {
		return NAT[nation];
	}

	public void setNation(Integer nation) {
		this.nation = nation;
	}

	public void setNation(String nation) {
		for (int i = 0; i < NATLEN; i++) {
			if (nation.equals(NAT[i])) {
				this.nation = i;
				return;
			}
		}
	}

	public String getPainting() {
		return painting;
	}

	public void setPainting(String painting) {
		this.painting = painting;
	}

	public Integer get_package() {
		return _package;
	}

	public String get_packageStr() {
		return PACK[_package];
	}

	public void set_package(Integer _package) {
		this._package = _package;
	}

	public void set_package(String _package) {
		for (int i = 0; i < PACKLEN; i++) {
			if (_package.equals(PACK[i])) {
				this._package = i;
				return;
			}
		}
		this._package = 12;
	}

	private final static int PACKLEN = 12;
	private final static int NATLEN = 5;
	public final static String PACK[] = { "标准包", "SR标准包", "天罡包", "地煞包", "人杰包", "特别包", "破军包", "魂烈包", "阴阳包", "贪狼包", "三英包",
			"将星包", "未知" };
	public final static String NAT[] = { "蜀", "吴", "魏", "群", "神", "未知" };

	@Override
	public String toString() {
		return "Hero [ids=" + ids + ", identify=" + identify + ", name=" + name + ", nation=" + nation + ", painting="
				+ painting + ", _package=" + _package + "]";
	}
}
