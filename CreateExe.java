import java.io.*;
import java.util.jar.*;
class OnlyExt implements FilenameFilter{
  String ext;
  public OnlyExt(String ext){
  this.ext="." + ext;
  }
  public boolean accept(File dir,String name){
  return name.endsWith(ext);
  }
}
public class CreateExe {
public static int buffer = 10240;
protected void create(File exefile, File[] listFiles) {
try {
byte b[] = new byte[buffer];
FileOutputStream fout = new FileOutputStream(exefile);
JarOutputStream out = new JarOutputStream(fout, new Manifest());
for (int i = 0; i < listFiles.length; i++) {
if (listFiles[i] == null || !listFiles[i].exists()|| listFiles[i].isDirectory())
System.out.println("Adding " + listFiles[i].getName());
JarEntry addFiles = new JarEntry(listFiles[i].getName());
addFiles.setTime(listFiles[i].lastModified());
out.putNextEntry(addFiles);

FileInputStream fin = new FileInputStream(listFiles[i]);
while (true) {
int len = fin.read(b, 0, b.length);
if (len <= 0)
break;
out.write(b, 0, len);
}
fin.close();
}
out.close();
fout.close();
System.out.println("Jar File is created successfully.");
} catch (Exception ex) {}
}
public static void main(String[]args){
CreateExe exe=new CreateExe();
FilenameFilter ff = new OnlyExt("class");
File folder = new File("c:/Examples");
File[] files = folder.listFiles(ff);
File file=new File("C:/Examples/Examples.exe");
exe.create(file, files);
}

}