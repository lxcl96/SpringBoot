$ret = RunWait("C:\Users\admin\Desktop\runlog\ztScriptAssait.exe")
If $ret = 0 Then
   MsgBox(0,"","exe脚本执行失败,退出代码：0")
EndIf