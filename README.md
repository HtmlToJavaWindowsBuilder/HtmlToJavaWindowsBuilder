HtmlToJavaWindowsBuilder
========================

"hw1" for "SOFTWARE DEVELOPMENT FOR LARGE AND OPEN SOURCE PROJECTS"

使用方法: 
1. 安裝Eclipse:
   先下載"Eclipse" http://www.eclipse.org/downloads/，建議使用Eclipse Classic 4.2.1，點選Windows 32 Bit or Windows 64    
Bit下載
2. 解壓縮後，點選Eclipse圖示執行。
   接著安裝"Egit":
   最上面一排點選Help -> Install New Software -> Add 
   -> Name[Egit] Location[http://download.eclipse.org/egit/updates] -> 點選OK 
   -> 此時下面點選[Eclipse Git Team Provider][JGit] -> next...finish
3. "Import" Github上的repository:
   File -> import -> Git -> Projects from Git -> URI填入https://github.com/HtmlToJavaWindowsBuilder/HtmlToJavaWindowsBuilder.git 
   -> next到底 -> finish
   附上教學網址：http://puremonkey2010.blogspot.tw/2012/05/eclipse-plugin-egit-github-import.html
4. check out branch到hw1:
   在Package Explore裡的Project上點右鍵 -> Team -> Switch to -> Other -> Remote Tracking -> origin/hw1 -> 點選check out
5. 執行Unit Test:
   Project裡的test資料夾 -> (default package) -> AllTests.java上點右鍵 -> Run As -> JUnit Test