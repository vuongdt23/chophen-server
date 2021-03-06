package com.uit.chophen.dataseed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.uit.chophen.dao.UniversityDAO;
import com.uit.chophen.entities.University;

@Component
public class UniversityDataLoader implements CommandLineRunner {

	private UniversityDAO universityDAO;

	@Autowired
	public UniversityDataLoader(UniversityDAO universityDAO) {
		this.universityDAO = universityDAO;
	}

	@Transactional
	private void loadUniversityData() {
		University uit = new University();
		University ussh = new University();
		University hcmus = new University();
		University hcmut = new University();
		University iu = new University();
		University nlu = new University();
		University uel = new University();
		University ute = new University();

		uit.setUniversityAbbName("UIT");
		uit.setUniversityName("Trường Đại học Công Nghệ Thông Tin - Đại học Quốc Gia TP.HCM");
		uit.setUniversityEmailSuffix("@gm.uit.edu.vn");
		ussh.setUniversityAbbName("USSH");
		ussh.setUniversityName("Trường Đại học Khoa học Xã hội và Nhân văn - Đại học Quốc Gia TP.HCM");
		ussh.setUniversityEmailSuffix("@hcmussh.edu.vn");
		hcmus.setUniversityAbbName("HCMUS");
		hcmus.setUniversityName("Trường Đại học Khoa học Tự nhiên - Đại học Quốc Gia TP.HCM");
		hcmus.setUniversityEmailSuffix("@student.hcmus.edu.vn");
		hcmut.setUniversityAbbName("HCMUT");
		hcmut.setUniversityName("TTrường Đại học Bách Khoa - Đại học Quốc Gia TP.HCM");
		hcmut.setUniversityEmailSuffix("@hcmut.edu.vn");
		iu.setUniversityAbbName("IU");
		iu.setUniversityName("Trường Đại học Quốc tế - Đại học Quốc Gia TP.HCM");
		iu.setUniversityEmailSuffix("@hcmiu.edu.vn");
		nlu.setUniversityAbbName("NLU");
		nlu.setUniversityName("Trường Đại học Nông Lâm");
		nlu.setUniversityEmailSuffix( "@hcmuaf.edu.vn");
		uel.setUniversityAbbName("UEL");
		uel.setUniversityName("Trường Đại học Kinh tế - Luật - Đại học Quốc Gia TP.HCM");
		uel.setUniversityEmailSuffix("@st.uel.edu.vn");
		ute.setUniversityAbbName("UTE");
		ute.setUniversityName("Trường Đại học Sư phạm Kỹ thuật TP.HCM");
		ute.setUniversityEmailSuffix("@student.hcmute.edu.vn");

		universityDAO.save(uit);
		universityDAO.save(ussh);

		universityDAO.save(hcmus);

		universityDAO.save(hcmut);

		universityDAO.save(uel);

		universityDAO.save(iu);

		universityDAO.save(ute);

		universityDAO.save(nlu);

	}

	@Override
	public void run(String... args) throws Exception {
		if(universityDAO.getCount()== 0) {
			loadUniversityData();
		}
		else {
			return;
		}

	}

}
