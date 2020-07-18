package org.ibs.cds.gode.terminal.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

public class LibraryLoader {

    private final static Set<String> libraries;

    static{
        libraries = Set.of(
                "libpty/freebsd/x86/libpty.so", "libpty/freebsd/x86_64/libpty.so",
                "libpty/linux/x86/libpty.so", "libpty/linux/x86_64/libpty.so",
                "libpty/macosx/x86/libpty.dylib", "libpty/macosx/x86_64/libpty.dylib",
                "libpty/win/x86/winpty.dll", "libpty/win/x86/winpty-agent.exe",
                "libpty/win/x86_64/winpty.dll", "libpty/win/x86_64/winpty-agent.exe", "libpty/win/x86_64/cyglaunch.exe",
                "libpty/win/xp/winpty.dll", "libpty/win/xp/winpty-agent.exe"
        );
    }

    public static synchronized void load(Path dataDir) throws IOException {
        Path donePath = dataDir.resolve(".DONE");
        if (!Files.exists(donePath)) {
            libraries.stream().forEach(each->{
                Path nativePath = dataDir.resolve(each);
                copy(each, nativePath);
            });
            Files.createFile(donePath);
        }
    }

    private static void copy(String library, Path nativePath) {
       try {
           if (Files.notExists(nativePath)) {
               Files.createDirectories(nativePath.getParent());
               InputStream inputStream = LibraryLoader.class.getResourceAsStream("/" + library);
               Files.copy(inputStream, nativePath);
               try{
                   inputStream.close();
               }catch (Exception e){

               }
           }
       }catch (Exception e){
           throw new RuntimeException(e);
       }
    }

}
