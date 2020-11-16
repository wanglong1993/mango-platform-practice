set targetWarPath=D:\web\mango-platform-practice\server
@echo off    #关闭显示
@echo 查找目录...
 
@echo 当前目录为:%cd%
cd mango-admin
@echo mvn install
cd ../huap-parent
call mvn clean install