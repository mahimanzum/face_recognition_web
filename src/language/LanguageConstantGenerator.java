package language;
import java.io.File;
import java.io.PrintWriter;
import java.util.*;


import permission.MenuRepository;
import permission.MenuUtil;


public class LanguageConstantGenerator {
	
	
	public static final String PACKAGE_NAME = "language";
	public static final String FILE_NAME = "LC";
	public static void main(String[] arrgs) throws Exception{
		MenuUtil menuUtil = new MenuUtil();
		List<Integer> list = (List<Integer>)new LanguageDAO().getIDs(null);
		List<LanguageTextDTO> languageDTOList = (List<LanguageTextDTO>)new LanguageDAO().getDTOs(list, null);
		
		String fileAbsolutePath  = "src/"+PACKAGE_NAME.replaceAll("\\.", "/")+"/"+FILE_NAME+".java";
		
		StringBuilder stringBuilder = new StringBuilder("package "+PACKAGE_NAME+";\n\npublic class "+FILE_NAME+"{\n");
		ArrayList<String> addedElement = new ArrayList<String>();
		for(LanguageTextDTO languageDTO : languageDTOList)
		{
			String Constant = languageDTO.languageConstantPrefix + "_" + languageDTO.languageConstant;
			if(Constant.contains(" ") || addedElement.contains(Constant))
			{
				continue;
			}			
			stringBuilder.append("\tpublic static final int ").append(languageDTO.languageConstantPrefix).append("_").append(languageDTO.languageConstant).append("=").append(languageDTO.ID).append(";\n");
			addedElement.add(Constant);
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
