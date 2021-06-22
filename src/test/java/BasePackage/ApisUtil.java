package BasePackage;

import java.util.HashMap;
import java.util.Map;

public class ApisUtil extends BaseClass{

	static Map<String, Object> params = new HashMap<String, Object>();
	
	public static void CreateUser() {
//		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "Virender");
		params.put("job", "QA");		
		PostRequest(Apis.CreateUser, params);
	}
}
