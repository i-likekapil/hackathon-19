package com.hackathon.pragati;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.SetOptions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.hackathon.pragati.SplashScreen.firebaseAuth;
import static com.hackathon.pragati.SplashScreen.firestore;
public class StateList {
    String[] Andhra_Pradesh = new String[]{"Visakhapatnam", "Vijayawada", "Guntur", "Rajahmundry", "Nellore", "Kakinada", "Kurnool", "Kadapa", "Tirupati", "Eluru", "Anantapur", "Vizianagaram", "Proddatur", "Nandyal", "Ongole", "Tenali", "Adoni", "Madanapalle", "Machilipatnam", "Chittoor", "Hindupur", "Bhimavaram", "Srikakulam", "Anakapalli", "Dharmavaram", "Gudivada", "Narasaraopet", "Tadipatri", "Tadepalligudem", "Chilakaluripet", "Amaravati", "Kavali"};
    String[] Arunachal_Pradesh = new String[]{"Along", "Basar", "Bomdila", "Itanagar", "Khonsa", "Margherita", "Naharlagun", "Pasighat", "Tawang", "Tezu", "Ziro"};
    String[] Assam = new String[]{"Tamulpur", "Morigaon", "Jorhat", "Mariani", "Teok", "Majuli", "Titabor", "Lakhimpur", "Bihpuria", "Dhakuakhona", "Sivasagar", "Amguri", "Simaluguri", "Charaideo", "Moranhat", "Nazira", "Sonitpur", "Rangapara", "Dhekiajuli", "Gohpur", "Biswnath", "Gohpur", "Hailakandi"};
    String[] Bihar = new String[]{"Bhojpur", "Aurangabad", "Bagaha", "Begusarai", "Bettiah", "Bhagalpur", "Bihar", "Buxar", "Chhapra", "Danapur", "Darbhanga", "Dehri", "Gaya", "Hajipur", "Jamalpur", "Jehanabad", "Katihar", "Kishanganj", "Motihari", "Munger", "Muzaffarpur", "Nawada", "Patna", "Purnia", "Saharsa", "Sasaram", "Sitamarhi", "Siwan"};
    String[] Chhattisgarh = new String[]{"Bastar", "Bijapur", "Bilaspur", "Dantewada", "Dhamtari", "Bhilai-Durg", "Janjgir-champa", "Jashpur", "Kabeerdham", "Korba", "Koriya", "Mahasamund", "Narayanpur", "Raigarh", "Raipur", "Rajnandgaon", "Sarguja", "Kanker"};
    String[] Delhi_NCR ={"Delhi","Ghaziabad","Faridabad","Gurugram","Noida","Meerut","Bulandshahr","Panipat","Alwar"};
    String[] Goa = new String[]{"Bicholim", "Canacona", "Cuncolim", "Curchorem", "Mapusa", "Margao", "Mormugao", "Panaji", "Pernem", "Ponda", "Quepem", "Sanguem", "Sanquelim", "Valpoi"};
    String[] Gujarat = new String[]{"Ahmedabad", "Anand", "Bharuch", "Bhavnagar", "Gandhidham", "Jamnagar", "Junagadh", "Morvi", "Nadiad", "Navsari", "Porbandar", "Rajkot", "Surat", "Surendranagar", "Vadodara"};
    String[] Haryana = new String[]{"Ambala", "Bahadurgarh", "Bhiwani", "Faridabad", "Gurugram", "Hisar", "Jind", "Kaithal", "Karnal", "Kosli", "Palwal", "Panchkula", "Panipat", "Pundri", "Rewari", "Rohtak", "Sirsa", "Sonipat", "Thanesar", "Yamunanagar"};
    String[] Himachal_Pradesh = new String[]{"Shimla", "Kufri", "Kullu Manali", "Rohtang Pass", "Dharamshala", "Dalhousie", "Khajjiar", "Chail", "Kasauli", "Lahaul", "Solan", "Palampur", "Chamba", "Kangra Valley", "Hamirpur", "Kinnaur", "Mandi", "Nahan", "Nalagarh", "Parwanoo", "Pathankot", "Pragpur", "Una", "Manali", "Kullu", "Spiti"};
    String[] Jammu_and_Kashmir = new String[]{"Jammu", "Srinagar", "Leh Ladakh", "Gulmarg", "Sonamarg", "Pahalgam", "Amarnath"};
    String[] Jharkhand = new String[]{"Bokaro", "Chakradharpur", "Chirkunda", "Deoghar", "Dhanbad", "Giridih", "Hazaribagh", "Jamshedpur", "Medininagar", "Phusro", "Ramgarh", "Ranchi"};
    String[] Karnataka = new String[]{"Bagalkot", "Ballari", "Bangalore", "Belagavi", "Bhadravati", "Bidar", "Chikmagalur", "Chitradurga", "Davanagere", "Gadag-Betageri", "Gangavati", "Hassan", "Hosapete", "Hubballi-Dharwad", "kalaburagi", "Kolar", "Mandya", "Mangalore", "Mysuru", "Raichur", "Ranebennuru", "Robertsonpet", "Shivamogga", "Tumkur", "Udupi", "vijayapura"};
    String[] Kerala = new String[]{"Kochi", "Kozhikode", "Thiruvananthapuram", "Kollam", "Thrissur", "Kannur", "Alappuzha", "Kottayam", "Palakkad", "Changanassery", "Malappuram", "Manjeri", "Kodungallur", "Thalassery", "Thrippunithura", "Ponnani", "Thrikkakkara", "Vatakara", "Kanhangad", "Taliparamba", "Koyilandy", "Neyyattinkara", "Kalamassery", "Kayamkulam", "Beypore"};
    String[] Madhya_Pradesh = new String[]{"Indore", "Bhopal", "Jabalpur", "Gwalior", "Ujjain", "Sagar", "Dewas", "Satna", "Ratlam", "Rewa", "Murwara", "Singrauli", "Burhanpur", "Khandwa", "Bhind", "Chhindwara", "Guna", "Shivpuri", "Vidisha", "Chhatarpur", "Damoh", "Mandsaur", "Khargone", "Neemuch", "Pithampur", "Hoshangabad", "Itarsi", "Sehore", "Betul", "Seoni", "Datia", "Nagda"};
    String[] Maharashtra = new String[]{"Achalpur", "Ahmednagar", "Akola", "Ambarnath", "Amravati", "Aurangabad", "Badlapur", "Barshi", "Beed", "Bhiwandi-Nizampur", "Bhusawal", "Chandrapur", "Dhule", "Gondia", "Hinganghat", "Ichalkaranji", "Jalgaon", "Jalna", "Kalyan-Dombivli", "Kolhapur", "Latur", "Malegaon", "Mira-Bhayandar", "Mumbai", "Nagpur", "Nanded-Waghala", "Nandurbar", "Nashik", "Navi-Mumbai", "Osmanabad", "Panvel", "Parbhani", "Pimpri-Chinchwad", "Pune", "Sangli-Miraj-Kupwad", "Satara", "Solapur", "Thane", "Udgir", "Ulhasnagar", "Vasai-Virar", "Wardha", "Yavatmal"};
    String[] Manipur = new String[]{"Churachandpur", "Imphal", "Kakching", "Moirang", "Phek", "Thoubal", "Wangjing", "Yairipok"};
    String[] Meghalaya = new String[]{"Baghmara", "Cherrapunjee", "Jowai", "Lawsohtun", "Madanriting", "Mairang Town", "Mawlai", "Mawpat", "Nongkseh", "Nongmynsong", "Nongpoh Town", "Nongstoin", "Nongthymmai", "Pynthormukhrah", "Resubelpara", "Shillong", "Tura", "Umlyngka", "Umpling", "Umroi", "Williamnagar"};
    String[] Mizoram = new String[]{"Aizawl", "Bairabi", "Biate", "Champhai", "Darlawn", "Hnahthial", "Khawhai", "Khawzawl", "Kolasib", "Lawngtlai", "Lengpui", "Lunglei", "Mamit", "N. Kawnpui", "North Vanlaiphai", "Saiha", "Sairang", "Saitual", "Serchhip", "Thenzawl", "Tlabung", "Vairengte", "Zawlnuam"};
    String[] Nagaland = new String[]{"Changtongya", "Chumukedima", "Dimapur", "Diphupar", "Jalukie", "Kiphire", "Kohima", "Kuda", "Longleng", "Medziphema", "Mokokchung", "Mon Town", "Naginimora", "Peren", "Pfutsero", "Phek", "Puranabazar", "Rangapahar", "Satakha", "Tseminyu", "Tsudikong", "Tuensang", "Tuli"};
    String[] Odisha = new String[]{"Balangir", "Balasore", "Bargarh", "Baripada", "Bhadrak", "Bhawanipatna", "Bhubaneswar", "Brahmapur", "Cuttack", "Jeypore", "Jharsuguda", "Puri", "Rayagada", "Rourkela", "Sambalpur"};
    String[] Rajasthan = new String[]{"Ajmer", "Alwar", "Bharatpur", "Bhilwara", "Bikaner", "Hanumangarh", "Jaipur", "Jodhpur", "Kota", "sikar", "Sri Ganganagar", "Udaipur"};
    String[] Punjab = new String[]{"Amritsar", "Barnala", "Bathinda", "Faridkot", "Fatehgarh", "Firozpur", "Gurdaspur", "Hoshiarpur", "Jalandhar", "Kapurthala", "Ludhiana", "Mansa", "Moga", "Muktsar", "Patiala", "Rupnagar", "Sahibzada", "Sangrur", "Shahid Bhagat Singh Nagar", "Tarn Taran"};
    String[] Sikkim = new String[]{"East Sikkim", "West Sikkim", "North Sikkim", "South Sikkim"};
    String[] Tamil_Nadu = new String[]{"Chennai", "Ooty", "Coonoor", "Tanjore", "Mahabalipuram", "Kodaikanal", "Madurai", "Kanyakumari", "Trichy", "Coimbatore", "Dharmapuri", "Kancheepuram", "Chettinad", "Kumbakonam", "Krishnagiri", "Poompuhar", "Puddukottai", "Rameswaram", "Sivagangai", "Theni", "Tirunelveli", "Thoothukudi", "Tiruvarur", "Velankanni", "Vellore", "Virudhunagar", "Tiruvannamalai"};
    String[] Telangana = new String[]{"Hyderabad", "Warangal", "Adilabad", "Karimnagar", "Khammam", "Nalgonda", "Nizamabad"};
    String[] Tripura = new String[]{"Agartala", "Amarpur", "Ambassa", "Anandanagar", "Bankimnagar", "Bishalgarh", "Briddhanagar", "Chandigarh", "Charipara", "Dewanpasa", "Dharmanagar", "Dhwajnagar", "Dukli", "Fatikroy", "Fulkumari", "Gakulnagar", "Gakulpur", "Gandhigram", "Kailasahar", "Kalachhari", "Kamalpur", "Kanchanpur", "Lebachhara", "Madhuban", "Madhupur", "Manu", "Matarbari", "Narsingarh", "Panisagar", "Radhakishorenagar", "Ranirbazar", "Sabroom Nagar", "Santir Bazar", "Singarbil", "Sonamura", "Taranagar", "Teliamura", "Udaipur Nagar", "Uttar Champamura"};
    String[] Uttar_Pradesh = new String[]{"Agra", "Akbarpur", "Aligarh", "Amroha", "Awagarh", "Ayodhya", "Azamgarh", "Bahraich", "Ballia", "Banda", "Barabanki", "Bareilly", "Basti", "Bijnor", "Budaun", "Bulandshahr", "Chandausi", "Deoria", "Etah", "Etawah", "Farrukhabad-cum-Fategarh", "Fatehpur", "Firozabad", "Ghaziabad", "Ghazipur", "Gonda", "Gorakhpur", "Greater Noida", "Hapur", "Hardoi", "Hathras", "Jaunpur", "Jhansi", "Kanpur", "Kasganj", "Khurja", "Lakhimpur", "Lalitpur", "Lucknow", "Mainpuri", "Mathura", "Meerut", "Mirzapur-cum-Vindhyachal", "Modinagar", "Moradabad", "Mughalsarai", "Muzaffarnagar", "Noida", "Orai", "Pilibhit", "Prayagraj", "Raebareli", "Rampur", "Saharanpur", "Sahaswan", "Sambhal", "Shahjahanpur", "Shamli", "Shikohabad", "Sitapur", "Sultanpur", "Tanda", "Ujhani", "Unnao", "Varanasi"};
    String[] Uttarakhand = new String[]{"Dehradun", "Haridwar", "Roorkee", "Haldwani-cum-Kathgodam", "Rudrapur", "Kashipur", "Rishikesh"};
    String[] West_Bengal = new String[]{"Alipurduar", "Asansol", "Baharampur", "Balurghat", "Bangaon", "Bankura", "Bardhaman", "Basirhat", "Chakdaha", "Cooch Behar", "Dankuni", "Darjeeling", "Dhulian", "Durgapur", "Habra", "Haldia", "Jalpaiguri", "Jangipur", "Kharagpur", "Kolkata", "Krishnanagar", "Medinipur", "Nabadwip", "Purulia", "Raiganj", "Ranaghat", "Shantipur", "Siliguri"};
    String[][] list = {Andhra_Pradesh, Arunachal_Pradesh, Assam, Bihar,Delhi_NCR, Chhattisgarh, Goa, Gujarat, Haryana, Himachal_Pradesh, Jammu_and_Kashmir, Jharkhand, Karnataka, Kerala, Madhya_Pradesh, Maharashtra, Manipur, Meghalaya, Mizoram, Nagaland, Odisha, Rajasthan, Punjab, Sikkim, Tamil_Nadu, Telangana, Tripura, Uttar_Pradesh, Uttarakhand, West_Bengal};
    String[] listState = {"Andhra_Pradesh", "Arunachal_Pradesh", "Assam", "Bihar","Delhi_NCR", "Chhattisgarh", "Goa", "Gujarat", "Haryana", "Himachal_Pradesh", "Jammu_and_Kashmir", "Jharkhand", "Karnataka", "Kerala", "Madhya_Pradesh",
            "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Rajasthan", "Punjab", "Sikkim", "Tamil_Nadu", "Telangana", "Tripura", "Uttar_Pradesh", "Uttarakhand", "West_Bengal"};


    public void write() {
        for (int i = 0; i < list.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("cities", Arrays.asList(list[i]));
            final int finalI = i;
            firestore.document("India/" + listState[i]).set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    System.out.println(listState[finalI]);
                    //Toast.makeText(SplashScreen.this, listState[i], Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}














