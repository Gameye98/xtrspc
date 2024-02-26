package sec.blackhole.xtrspc;

import android.app.Activity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import android.widget.TextView;
import android.widget.ScrollView;
import android.widget.Button;
import android.widget.EditText;
import android.text.Html;
import android.view.View;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import android.graphics.Typeface;
import android.app.ProgressDialog;
import android.widget.Toast;
import java.util.zip.ZipOutputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.util.zip.ZipEntry;
import java.util.Base64;
import java.util.Arrays;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;

public class MainActivity extends AppCompatActivity {
	String homedir = "/sdcard/data/xtrspc";
	String appFilesDir = "/data/data/sec.blackhole.xtrspc";
	String appPrivDir = appFilesDir+"/files";
	String endl = "\012";
	private String digits = "0123456789";
	String[] binaries;
	TextView xterm;
	ScrollView tscroll;
	Button compare;
	EditText file1,file2;
    boolean isreinstall = false;
    List<File> filesWalk = new ArrayList<File>();
    public int STORAGE_PERMISSION_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
		}
        setTitle("xtrspc");
		xterm = findViewById(R.id.xterm);
		tscroll = findViewById(R.id.tscroll);
		compare = findViewById(R.id.compare);
		file1 = findViewById(R.id.file1);
        file1.setHint("Old File");
		file2 = findViewById(R.id.file2);
        file2.setHint("New File");
		// Set Typeface
        Typeface tf = Typeface.createFromAsset(getAssets(),"ubuntu.ttf");
        xterm.setTypeface(tf);
        file1.setTypeface(tf);
        file2.setTypeface(tf);
		// Set FontSize
        xterm.setTextSize(12);
		try {
			InputStream stream = getAssets().open("bin/binaries.txt");
			int size = stream.available();
			byte[] buffer = new byte[size];
			stream.read(buffer);
			stream.close();
			String content = new String(buffer);
			binaries = content.split("\n");
		} catch(Exception e) {
			binaries = new String[]{};
		}
		compare.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
                try {
                    xterm.setText(""); // Clear screen
                    //String banner = "ICAgICAgICAgX18gICAgICAgICAgICAgICAgICAgICAgICAgCiAgX18gX18gfCAgfF8gIF9fX18g$IF9fX19fICBfX19fXyAgX19fXwogfF8gYCBffHwgICBffHwgICBffHxfXyAtLXx8ICBfICB8fCAg$X198IGF1dGhvcjogR2FtZXllOTggKDEuMC1kZXYpCiB8X18uX198fF9fX198fF9ffCAgfF9fX19f$fHwgICBfX3x8X19fX3wgR3I4IHQwMGx6IDQgQ29tcGFyZSBBUEsKICAgICAgICAgICAgICAgICAg$ICAgICAgICAgfF9ffCAgICAgICAgClsrXSBNeSBQcm9maWxlOiBodHRwczovL2dpdGh1Yi5jb20v$R2FtZXllOTgKWytdIEJsYWNrSG9sZSBTZWN1cml0eSAoaHR0cHM6Ly9naXRodWIuY29tL0JsYWNr$SG9sZVNlY3VyaXR5KQpbK10gU2NoYWRlbmZyZXVkZSAoaHR0cHM6Ly90Lm1lL3NjaGRlbmZyZXVk$ZSAvIEBzY2hkZW5mcmV1ZGUpCg==$";
                    //banner = new String(Base64.getDecoder().decode(banner.replace("$","\n")));
                    //printf(banner,"txt");
                    printf(new String(Base64.getDecoder().decode("ICAgICAgICAgX18gICAgICAgICAgICAgICAgICAgICAgICAgCiAgX18gX18gfCAgfF8gIF9fX18g")),"txt");
                    printf(new String(Base64.getDecoder().decode("IF9fX19fICBfX19fXyAgX19fXwogfF8gYCBffHwgICBffHwgICBffHxfXyAtLXx8ICBfICB8fCAg")),"txt");
                    printf(new String(Base64.getDecoder().decode("X198IGF1dGhvcjogR2FtZXllOTggKDEuMC1kZXYpCiB8X18uX198fF9fX198fF9ffCAgfF9fX19f")),"txt");
                    printf(new String(Base64.getDecoder().decode("fHwgICBfX3x8X19fX3wgR3I4IHQwMGx6IDQgQ29tcGFyZSBBUEsKICAgICAgICAgICAgICAgICAg")),"txt");
                    printf(new String(Base64.getDecoder().decode("ICAgICAgICAgfF9ffCAgICAgICAgClsrXSBNeSBQcm9maWxlOiBodHRwczovL2dpdGh1Yi5jb20v")),"txt");
                    printf(new String(Base64.getDecoder().decode("R2FtZXllOTgKWytdIEJsYWNrSG9sZSBTZWN1cml0eSAoaHR0cHM6Ly9naXRodWIuY29tL0JsYWNr")),"txt");
                    printf(new String(Base64.getDecoder().decode("SG9sZVNlY3VyaXR5KQpbK10gU2NoYWRlbmZyZXVkZSAoaHR0cHM6Ly90Lm1lL3NjaGRlbmZyZXVk")),"txt");
                    printf(new String(Base64.getDecoder().decode("ZSAvIEBzY2hkZW5mcmV1ZGUpCg==")),"txt");
                    printf(endl,"txt");
                    if(file1.getText().toString().startsWith("!")) {
                        switch(file1.getText().toString()) {
                            case "!reinstall":
                                isreinstall = true;
                                installBinary();
                                break;
                        }
                    } else {
                        try {
                            xtrspcTask t = new xtrspcTask();
                            t.execute(file1.getText().toString(),file2.getText().toString());
                        } catch(Exception e) {
                            printf(e.toString(),"txt");
                        }
                    }
                } catch(Exception e) {
                    printf(e.toString()+endl,"txt");
                }
			}
		});
        configSet();
    }
	public void printf(String text,String format) {
		if(format.equals("txt")) {
			xterm.append(text);
		} else if(format.equals("html")) {
			xterm.append(Html.fromHtml(text));
		}
		scroll();
	}
	public void scroll() {
		tscroll.post(new Runnable() {
			@Override
			public void run() {
				tscroll.fullScroll(View.FOCUS_DOWN);
			}
		});
	}
	public void configSet() {
		File dataDir = new File("/sdcard/data");
		File bhsApp = new File(homedir);
		if(dataDir.exists()) {
			if(dataDir.isFile()) {
				String randId = "";
				while(randId.length() < 5) {
					int index = (int)Math.floor(Math.random()*digits.length());
					randId += digits.substring(index, index+1);
				}
				dataDir.renameTo(new File("/sdcard/data-"+randId+".bak"));
				dataDir.mkdirs();
			}
		} else {
			dataDir.mkdir();
		}
		if(bhsApp.exists()) {
			if(bhsApp.isFile()) {
				bhsApp.delete();
				bhsApp.mkdirs();
			}
		} else {
			bhsApp.mkdirs();
		}
		// Make private directory
		// /data/user/0/package || /data/data/package
		String[] subdirs = {"bin"};
		for(int x = 0;x < subdirs.length;x++) {
			File subdirectory = new File(appFilesDir, subdirs[x]);
			subdirectory.mkdirs();
			subdirectory.setReadable(true,false);
			subdirectory.setWritable(true,false);
			subdirectory.setExecutable(true,false);
		}
		if(!isInstalled()) {
			installBinary();
		}
		/*
		LibsSetup libsSetup = new LibsSetup(this);
		if(!libsSetup.isInstalled()) {
			libsSetup.execute();
		}
		*/
	}
	public String randomID() {
		String randId = "";
		while(randId.length() < 5) {
			int index = (int)Math.floor(Math.random()*digits.length());
			randId += digits.substring(index, index+1);
		}
		return randId;
	}
	public boolean isInstalled() {
		boolean installed = true;
		try {
			for(String binary : binaries) {
				int f1 = getAssets().open("bin/"+binary).available();
				int f2 = new FileInputStream(new File(appFilesDir+"/bin",binary)).available();
				if(f1 != f2) {
					installed = false;
					break;
				}
			}
		} catch(Exception e) {
			printf(e.toString()+endl,"txt");
			installed = false;
		}
		return installed;
	}
	public void installBinary() {
        ProgressDialog pdia = new ProgressDialog(this);
        pdia.setMessage("Installing...");
        pdia.setCancelable(false);
        pdia.show();
		try {
			File binDir = new File(appFilesDir,"bin");
			if(binDir.exists()) {
				if(binDir.isFile()) {
					binDir.delete();
					binDir.mkdir();
				}
			} else {
				binDir.mkdir();
			}
			binDir.setReadable(true,false);
			binDir.setWritable(true,false);
			binDir.setExecutable(true,false);
			for(String binary : binaries) {
				printf("Installing "+binary+endl,"txt");
				InputStream stream = getAssets().open("bin/"+binary);
				int size = stream.available();
				byte[] buffer = new byte[size];
				stream.read(buffer);
				stream.close();
				new FileOutputStream(appFilesDir+"/bin/"+binary).write(buffer);
				File file = new File(appFilesDir+"/bin",binary);
				file.setReadable(true,false);
				file.setWritable(true,false);
				file.setExecutable(true,false);
				printf(binary+" installed."+endl,"txt");
			}
            pdia.dismiss();
            if(isreinstall) {
                Toast.makeText(getApplicationContext(), "Installation has been completed!",Toast.LENGTH_SHORT).show();
                isreinstall = false;
            }
		} catch(Exception e) {
            pdia.dismiss();
            isreinstall = false;
            Toast.makeText(getApplicationContext(), "Something went wrong...", Toast.LENGTH_SHORT).show();
            printf(e.toString()+endl,"txt");
		}
	}
    public void deleteRecursively(File file) {
        try {
            if(file.isDirectory()) {
                for(File f : file.listFiles()) {
                    deleteRecursively(f);
                }
            }
            file.delete();
        } catch(Exception e) {
            printf(e.toString()+endl,"txt");
        }
	}
    public void FileWalk(File file) {
        try {
            File[] fls = file.listFiles();
            for(File fi : fls) {
                if(fi.isDirectory()) {
                    FileWalk(fi);
                } else {
                    filesWalk.add(fi);
                }
            }
        } catch(Exception e) {
            printf(e.toString()+endl,"txt");
        }
    }
    public File[] fWalk(String f) {
        try {
            filesWalk.clear();
            FileWalk(new File(f));
            return filesWalk.toArray(new File[0]);
        } catch(Exception e) {
            printf(e.toString()+endl,"txt");
            return new File[]{};
        }
    }
    public class xtrspcTask extends AsyncTask<String,String,String>
	{

		@Override
		protected void onProgressUpdate(String[] values)
		{
			super.onProgressUpdate(values);
			printf(values[0],"txt");
		}
		@Override
		protected String doInBackground(String[] params)
		{
			try {
				File fpriv = new File(appPrivDir);
				if(fpriv.exists()) {
					if(fpriv.isFile()) {
						fpriv.delete();
						fpriv.mkdir();
					}
				} else {
					fpriv.mkdir();
				}
				String basePath = appPrivDir+"/tmp";
                File baseDir = new File(basePath);
                if(baseDir.exists()) {
                    if(baseDir.isFile()) {
                        baseDir.delete();
                        baseDir.mkdirs();
                    }
                } else {
                    baseDir.mkdirs();
				}
				for(File fls_ : new File(basePath).listFiles()) {
					if(fls_.isDirectory()) {
						String flsnm_ = fls_.getAbsolutePath().substring(0,fls_.getAbsolutePath().lastIndexOf("/")+1);
						publishProgress("* "+flsnm_+endl);
						if(flsnm_.startsWith("file-")) {
							publishProgress("Clean "+fls_.getAbsolutePath()+endl);
							deleteRecursively(fls_);
						}
					}
				}
				String file1path = params[0].trim();
				String file2path = params[1].trim();
                // Filter User Input
                if(!new File(file1path).isFile()) {
                    throw new Exception(file1path+": No such file exists");
                }
                if(!new File(file2path).isFile()) {
                    throw new Exception(file2path+": No such file exists");
                }
                if(Pattern.compile(Pattern.quote(" ")).matcher(file1path).find()) {
                    throw new Exception(file1path+": No whitespace allowed");
                }
                if(Pattern.compile(Pattern.quote(" ")).matcher(file2path).find()) {
                    throw new Exception(file2path+": No whitespace allowed");
                }
                // -----------------
				String file1name = file1path.substring(file1path.lastIndexOf("/")+1,file1path.length());
				String file2name = file2path.substring(file2path.lastIndexOf("/")+1,file2path.length());
                String randomNum = randomID();
				String file1Tmp = basePath+"/file1-"+randomNum;
                String file1dir = file1Tmp.substring(file1Tmp.lastIndexOf("/")+1,file1Tmp.length());
				String file2Tmp = basePath+"/file2-"+randomNum;
                String file2dir = file2Tmp.substring(file2Tmp.lastIndexOf("/")+1,file2Tmp.length());
				// Decompile APK 1
				String[] commands1 = ("dalvikvm -cp "+appFilesDir+"/bin/apktool.jar brut.apktool.Main -cp "+appFilesDir+"/bin/android.jar -p /system/framework/framework-res.apk -r d -o "+file1Tmp+" "+file1path).split(" ");
				ProcessBuilder pb1 = new ProcessBuilder().
					directory(new File(homedir)).
					command(commands1).
					redirectErrorStream(true);
				Process p1 = pb1.start();
				p1.waitFor();
				BufferedReader br1 = new BufferedReader(new InputStreamReader(p1.getInputStream()));
				String content1="";String inputLine1;
				while((inputLine1 = br1.readLine()) != null) {
					content1 += inputLine1 + endl;
				}
				publishProgress(content1);
				br1.close();
                File[] file1ls = fWalk(file1Tmp);
				// Decompile APK 2
				String[] commands2 = ("dalvikvm -cp "+appFilesDir+"/bin/apktool.jar brut.apktool.Main -cp "+appFilesDir+"/bin/android.jar -p /system/framework/framework-res.apk -r d -o "+file2Tmp+" "+file2path).split(" ");
				ProcessBuilder pb2 = new ProcessBuilder().
					directory(new File(homedir)).
					command(commands2).
					redirectErrorStream(true);
				Process p2 = pb2.start();
				p2.waitFor();
				BufferedReader br2 = new BufferedReader(new InputStreamReader(p2.getInputStream()));
				String content2="";String inputLine2;
				while((inputLine2 = br2.readLine()) != null) {
					content2 += inputLine2 + endl;
				}
				publishProgress(content2);
				br2.close();
                File[] file2ls = fWalk(file2Tmp);
                // Sets of Data
                // Type: added, removed, modified & unchanged
                // [0]=Name,[1]=Type
                List<String[]> allData = new ArrayList<String[]>();
                // Compare File -- (1) Base on Old File
                for(File f : file1ls) {
                    if(f.isDirectory()) {
                        continue;
                    }
                    File fx = new File(f.toString().replace(file1dir,file2dir));
                    String fn = f.toString().split(Pattern.quote(file1dir))[1];
                    while(fn.startsWith("/")) {
                        fn = fn.substring(1,fn.length());
                    }
                    if(fx.exists()) {
                        // File 1 Content
                        InputStream stream = new FileInputStream(f);
                        int size = stream.available();
                        byte[] buffer = new byte[size];
                        stream.read(buffer);
                        stream.close();
                        // File 2 Content
                        InputStream is = new FileInputStream(fx);
                        int si = is.available();
                        byte[] bu = new byte[si];
                        is.read(bu);
                        is.close();
                        if(Arrays.equals(buffer, bu)) {
                            publishProgress("[*] UNCHANGED: "+fn+endl);
                            allData.add(new String[]{fn,"unchanged",f.toString()});
                        } else {
                            publishProgress("[!] MODIFIED: "+fn+endl);
                            allData.add(new String[]{fn,"modified",f.toString()});
                        }
                    } else {
                        publishProgress("[-] REMOVED: "+fn+endl);
                        allData.add(new String[]{fn,"removed",f.toString()});
                    }
                }
                // Compare File -- (2) Base on New File
                for(File f : file2ls) {
                    if(f.isDirectory()) {
                        continue;
                    }
                    File fx = new File(f.toString().replace(file2dir,file1dir));
                    String fn = f.toString().split(Pattern.quote(file2dir))[1];
                    while(fn.startsWith("/")) {
                        fn = fn.substring(1,fn.length());
                    }
                    if(!fx.exists()) {
                        publishProgress("[+] ADDED: "+fn+endl);
                        allData.add(new String[]{fn,"added",f.toString()});
                    }
                }
                // Create Log
                StringBuilder added = new StringBuilder();
                added.append("+++++ Added files:"+endl);
                StringBuilder removed = new StringBuilder();
                removed.append("+++++ Removed files:"+endl);
                StringBuilder modified = new StringBuilder();
                modified.append("+++++ Modified files:"+endl);
                StringBuilder unchanged = new StringBuilder();
                unchanged.append("+++++ Unchanged files:"+endl);
                for(String[] sx : allData) {
                    switch(sx[1]) {
                        case "added":
                            added.append(sx[0]+endl);
                        break;
                        case "removed":
                            removed.append(sx[0]+endl);
                        break;
                        case "modified":
                            modified.append(sx[0]+endl);
                        break;
                        case "unchanged":
                            unchanged.append(sx[0]+endl);
                        break;
                    }
                }
                added.append(endl);
                removed.append(endl);
                modified.append(endl);
                unchanged.append(endl);
                // Generate Compare Result
                publishProgress("Generate compare result: "+homedir+"/xtrspc-"+file1name+"_"+file2name+".txt"+endl);
                FileOutputStream out = new FileOutputStream(homedir+"/xtrspc-"+file1name+"_"+file2name+".txt");
                out.write((added.toString()+removed.toString()+modified.toString()+unchanged.toString()).getBytes());
                out.close();
                // Extract specific file based on the compare result(added, modified)
                publishProgress("Remove unchanged files"+endl);
                for(File f : file1ls) {
                    if(f.isDirectory()) {
                        continue;
                    }
                    boolean isfetch = false;
                    for(String[] sx : allData) {
                        if(sx[2].equals(f.toString()) && (sx[1].equals("added")||sx[1].equals("modified"))) {
                            isfetch = true;
                            break;
                        }
                    }
                    if(!isfetch) {
                        f.delete();
                    }
                }
                for(File f : file2ls) {
                    if(f.isDirectory()) {
                        continue;
                    }
                    boolean isfetch = false;
                    for(String[] sx : allData) {
                        if(f.toString().endsWith(sx[0]) && (sx[1].equals("added")||sx[1].equals("modified"))) {
                            isfetch = true;
                            break;
                        }
                    }
                    if(!isfetch) {
                        f.delete();
                    }
                }
                file1ls = fWalk(file1Tmp);
                file2ls = fWalk(file2Tmp);
                publishProgress("Fetch and Compress important data from compare result"+endl);
                ZipOutputStream zipOut = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(homedir+"/xtrspc-"+file1name+"-old.zip")));
                BufferedInputStream origin = null;
                int blocksize = 1024;
                byte[] data = new byte[blocksize];
                for(File f : file1ls) {
                    if(f.isDirectory()) {
                        continue;
                    }
                    publishProgress("inflating: "+f.toString()+endl);
                    String fn = f.toString().split(Pattern.quote(file1dir))[1];
                    while(fn.startsWith("/")) {
                        fn = fn.substring(1,fn.length());
                    }
                    FileInputStream fis = new FileInputStream(f);
                    origin = new BufferedInputStream(fis, blocksize);
                    try {
                        ZipEntry entry = new ZipEntry(fn);
                        zipOut.putNextEntry(entry);
                        int count;
                        while((count = origin.read(data, 0, blocksize)) != -1) {
                            zipOut.write(data, 0, count);
                        }
                        zipOut.closeEntry();
                    } finally {
                        origin.close();
                    }
                }
                zipOut.finish();
                zipOut.close();
                zipOut = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(homedir+"/xtrspc-"+file2name+"-new.zip")));
                origin = null;
                data = new byte[blocksize];
                for(File f : file2ls) {
                    if(f.isDirectory()) {
                        continue;
                    }
                    publishProgress("inflating: "+f.toString()+endl);
                    String fn = f.toString().split(Pattern.quote(file2dir))[1];
                    while(fn.startsWith("/")) {
                        fn = fn.substring(1,fn.length());
                    }
                    FileInputStream fis = new FileInputStream(f);
                    origin = new BufferedInputStream(fis, blocksize);
                    try {
                        ZipEntry entry = new ZipEntry(fn);
                        zipOut.putNextEntry(entry);
                        int count;
                        while((count = origin.read(data, 0, blocksize)) != -1) {
                            zipOut.write(data, 0, count);
                        }
                        zipOut.closeEntry();
                    } finally {
                        origin.close();
                    }
                }
                zipOut.finish();
                zipOut.close();
                publishProgress("Clean "+file1Tmp+endl);
                deleteRecursively(new File(file1Tmp));
                publishProgress("Clean "+file2Tmp+endl);
                deleteRecursively(new File(file2Tmp));
                publishProgress("Done."+endl);
			} catch(Exception e) {
				publishProgress(e.toString()+endl);
			}
			return null;
		}
	}
}
