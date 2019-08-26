package image;

import annotation.ColumnName;
import annotation.PrimaryKey;
import annotation.TableName;

@TableName("image")
public class ImageDTO {
	@PrimaryKey
	@ColumnName("image.ID")
	public int ID;
	@ColumnName("image.URL")
	public String URL;
	@ColumnName("image.fileName")
	public String fileName;
	@ColumnName("image.filePath")
	public String filePath;
}
