HtmlToJavaWindowsBuilder
========================

##What's this?
這是一個能用HTML來寫視窗程式的Library

##Why to use?
希望會寫網頁的程式設計師可以直接套用此Library來撰寫視窗程式



##SOFTWARE DEVELOPMENT FOR LARGE AND OPEN SOURCE PROJECTS
###HW2
* [Reference Open Source Project] (https://github.com/HtmlToJavaWindowsBuilder/HtmlToJavaWindowsBuilder/wiki/Cookswing)
* [Architecture] (https://github.com/HtmlToJavaWindowsBuilder/HtmlToJavaWindowsBuilder/wiki/System-Architecture)
* Flow Chart: 
因為這是一個Library 所以沒有Flow Chart

---


### HW1  
####目的:
實作四種Sort Algorithm(Heap Sort ，Merge Sort ，Inertion Sort ，Bubble Sort) 並利用JUnit作單元測試

####使用方法:  

1.  **安裝Eclipse**  
    先下載[Eclipse](http://www.eclipse.org/downloads/)，建議使用[Eclipse Classic 4.2.1](http://www.eclipse.org/downloads/packages/eclipse-classic-421/junosr1)，點選Windows 32 Bit or Windows 64 Bit下載
2.  **安裝Egit**  
    最上面一排點選Help -> Install New Software -> Add-> Name[Egit] Location[http://download.eclipse.org/egit/updates] -> 點選OK -> 勾選Eclipse Git Team Provider以及JGit -> next...finish
3.  **"Import" Github上的repository**    
    File -> import -> Git -> Projects from Git -> URI -> URI填入https://github.com/HtmlToJavaWindowsBuilder/HtmlToJavaWindowsBuilder.git -> next到底 -> finish  
    附上[教學網址](http://puremonkey2010.blogspot.tw/2012/05/eclipse-plugin-egit-github-import.html)
4.  **check out branch hw1**  
    在Package Explore裡的Project上點右鍵 -> Team -> Switch to -> Other -> Remote Tracking -> origin/hw1 -> 點選check out
5.  **Unit Test**    
    Project裡的test資料夾 -> (default package) -> AllTests.java上點右鍵 -> Run As -> JUnit Test