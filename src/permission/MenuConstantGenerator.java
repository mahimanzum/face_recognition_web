package permission;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import language.LanguageDAO;
import language.LanguageTextDTO;

public class MenuConstantGenerator {
	
	public static final String PACKAGE_NAME = "permission";
	public static final String FILE_NAME = "MenuConstants";
	public static void main(String[] arrgs) throws Exception{
		MenuUtil menuUtil = new MenuUtil();
		List<MenuDTO> menuList = MenuRepository.getAllMenuList();
		
		String fileAbsolutePath  = "src/"+PACKAGE_NAME.replaceAll("\\.", "/")+"/"+FILE_NAME+".java";
		
		StringBuilder stringBuilder = new StringBuilder("package "+PACKAGE_NAME+";\n\npublic class "+FILE_NAME+"{\n");
		for(MenuDTO menuDTO: menuList){
			stringBuilder.append("\tpublic static final int ").append(menuDTO.constant).append("=").append(menuDTO.menuID).append(";\n");
		}
		stringBuilder.append("}\n");
		
		File file = new File(fileAbsolutePath);
		if(file.exists()){
			file.delete();
		}
		boolean fileCreated = file.createNewFile();
		System.out.println("file created "+fileCreated+", file path "+file.getAbsolutePath());
		PrintWriter writer = new PrintWriter(fileAbsolutePath, "UTF-8");
		writer.print(stringBuilder.toString());
		writer.close();
	}
}
