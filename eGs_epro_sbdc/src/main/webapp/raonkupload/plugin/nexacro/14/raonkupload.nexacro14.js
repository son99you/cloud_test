RAONKUPLOAD.raonkUploadNexacroObject=new RAONKUPLOAD.util.hashTable;function RAONKUPLOAD_AfterAddAllFile(a){try{RAONKUPLOAD.raonkUploadNexacroObject.getItem(a)._RAONKUPLOAD_AfterAddAllFile(a)}catch(b){}}function RAONKUPLOAD_AfterAddFile(a,b){try{RAONKUPLOAD.raonkUploadNexacroObject.getItem(a)._RAONKUPLOAD_AfterAddFile(a,b)}catch(c){}}function RAONKUPLOAD_BeforeAddFile(a,b){try{return RAONKUPLOAD.raonkUploadNexacroObject.getItem(a)._RAONKUPLOAD_BeforeAddFile(a,b)}catch(c){}}
function RAONKUPLOAD_BeforeDeleteFile(a,b){try{return RAONKUPLOAD.raonkUploadNexacroObject.getItem(a)._RAONKUPLOAD_BeforeDeleteFile(a,b)}catch(c){}}function RAONKUPLOAD_BeforeDownloadFile(a,b){try{return RAONKUPLOAD.raonkUploadNexacroObject.getItem(a)._RAONKUPLOAD_BeforeDownloadFile(a,b)}catch(c){}}function RAONKUPLOAD_BeforeOpenFile(a,b){try{return RAONKUPLOAD.raonkUploadNexacroObject.getItem(a)._RAONKUPLOAD_BeforeOpenFile(a,b)}catch(c){}}
function RAONKUPLOAD_BeforeUpload(a,b){try{return RAONKUPLOAD.raonkUploadNexacroObject.getItem(a)._RAONKUPLOAD_BeforeUpload(a,b)}catch(c){}}function RAONKUPLOAD_CreationComplete(a,b){try{RAONKUPLOAD.raonkUploadNexacroObject.getItem(a)._RAONKUPLOAD_CreationComplete(a,b)}catch(c){}}function RAONKUPLOAD_DownloadCompleteFile(a,b){try{RAONKUPLOAD.raonkUploadNexacroObject.getItem(a)._RAONKUPLOAD_DownloadCompleteFile(a,b)}catch(c){}}
function RAONKUPLOAD_DownloadCompleteAllFile(a,b){try{RAONKUPLOAD.raonkUploadNexacroObject.getItem(a)._RAONKUPLOAD_DownloadCompleteAllFile(a,b)}catch(c){}}function RAONKUPLOAD_OnError(a,b){try{RAONKUPLOAD.raonkUploadNexacroObject.getItem(a)._RAONKUPLOAD_OnError(a,b)}catch(c){}}function RAONKUPLOAD_UploadComplete(a){try{RAONKUPLOAD.raonkUploadNexacroObject.getItem(a)._RAONKUPLOAD_UploadComplete(a)}catch(b){}}
function RAONKUPLOAD_UploadingCancel(a,b){try{RAONKUPLOAD.raonkUploadNexacroObject.getItem(a)._RAONKUPLOAD_UploadingCancel(a,b)}catch(c){}}function RAONKUPLOAD_SelectItem(a,b){try{RAONKUPLOAD.raonkUploadNexacroObject.getItem(a)._RAONKUPLOAD_SelectItem(a,b)}catch(c){}}
if(!nexacro.RaonkUpload&&"Runtime"!=nexacro.Browser){nexacro.RaonkUpload=function(a,b,c,e,d,f,g,h,k){nexacro.Div.call(this,a,b,c,e,d,f,g,h,k);this.ShowViewAlign=this.ShowEditAlign=this.ImageQualityOverFileSize=this.ImageQualityChangedExtension=this.ImageQualityExtension=this.ImageQualityWorkerCount=this.ImageQualityQuality=this.Views=this.ViewerHandlerUrl=this.UserMessageView=this.UserMessageEdit=this.UseOpenEvent=this.AutoSort=this.FileSortAscDesc=this.FileSortField=this.UseFileSort=this.UseUploadingCancelEvent=
this.UseDownloadEvent=this.UseDeleteEvent=this.UseAfterAddEvent=this.UseAfterAddEndTimeEvent=this.UseAddEvent=this.UploadTransferWindowView=this.Timeout=this.StatusBarShowStatus=this.StatusBarShowLimit=this.StatusBar=this.SkinName=this.SizeColumnWidth=this.SilentUpload=this.ResumeUpload=this.ResumeDownload=this.RemoveContextItem=this.MultiFileSelect=this.Mode=this.MaxTotalFileSize=this.MaxTotalFileCount=this.MaxOneFileSize=this.ListViewDragAndDrop=this.ListViewDbclick=this.LargeFileColor=this.LargeFileText=
this.LargeFileMaxTotalSize=this.LargeFileMaxCount=this.LargeFileSize=this.Lang=this.RunTimes=this.InitServerXml=this.InitXml=this.ImgPreViewHeight=this.ImgPreViewWidth=this.ImgPreView=this.HideListInfo=this.HideContextMenu=this.HeaderBar=this.HandlerUrl=this.FolderNameRule=this.FileNameRule=this.FileMoveContextMenu=this.ExtensionArr=this.ExtensionAllowOrLimit=this.DownloadHandlerUrl=this.OpenUnchosen=this.DownloadUnchosen=this.DeleteUnchosen=this.Duplication=this.DisableDeleteConfirm=this.DevelopLangage=
this.DefaultDownloadPath=this.CustomWebFileColor=this.CssRootPath=this.CompleteEventResetUse=this.ButtonBarPosition=this.ButtonBarView=this.ButtonBarEdit=this.BorderStyle=this.AutomaticConnection=this.AllowedZeroFileSize=this.uploadID="";this.ZIndex=1E7;this._event_list={RAONKUPLOAD_AfterAddAllFile:1,RAONKUPLOAD_AfterAddFile:1,RAONKUPLOAD_BeforeAddFile:1,RAONKUPLOAD_BeforeDeleteFile:1,RAONKUPLOAD_BeforeDownloadFile:1,RAONKUPLOAD_BeforeOpenFile:1,RAONKUPLOAD_BeforeUpload:1,RAONKUPLOAD_CreationComplete:1,
RAONKUPLOAD_BeforeCreation:1,RAONKUPLOAD_DownloadCompleteFile:1,RAONKUPLOAD_DownloadCompleteAllFile:1,RAONKUPLOAD_OnError:1,RAONKUPLOAD_UploadComplete:1,RAONKUPLOAD_UploadingCancel:1,RAONKUPLOAD_SelectItem:1};this.RAONKUPLOAD_SelectItem=this.RAONKUPLOAD_UploadingCancel=this.RAONKUPLOAD_UploadComplete=this.RAONKUPLOAD_OnError=this.RAONKUPLOAD_DownloadCompleteAllFile=this.RAONKUPLOAD_DownloadCompleteFile=this.RAONKUPLOAD_BeforeCreation=this.RAONKUPLOAD_CreationComplete=this.RAONKUPLOAD_BeforeUpload=
this.RAONKUPLOAD_BeforeOpenFile=this.RAONKUPLOAD_BeforeDownloadFile=this.RAONKUPLOAD_BeforeDeleteFile=this.RAONKUPLOAD_BeforeAddFile=this.RAONKUPLOAD_AfterAddFile=this.RAONKUPLOAD_AfterAddAllFile=null};var _pRaonkUpload=nexacro._createPrototype(nexacro.Div,nexacro.RaonkUpload);nexacro.RaonkUpload.prototype=_pRaonkUpload;_pRaonkUpload._type_name="RaonkUpload";_pRaonkUpload.on_create_contents=function(){this._RAONKUPLOAD_BeforeCreation()};_pRaonkUpload.on_created_contents=function(){var a=this.getElement();
this.uploadID=(new Date).getTime()+"";RAONKUPLOAD.raonkUploadNexacroObject.setItem(this.uploadID,this);new RAONKUpload({Id:this.uploadID,Width:"100%",Height:a.height+"px",UploadHolder:a._handle.id,ZIndex:this.ZIndex,InterworkingModuleFirst:"N",AllowedZeroFileSize:this.AllowedZeroFileSize,AutomaticConnection:this.AutomaticConnection,BorderStyle:this.BorderStyle,ButtonBarEdit:this.ButtonBarEdit,ButtonBarView:this.ButtonBarView,ButtonBarPosition:this.ButtonBarPosition,CompleteEventResetUse:this.CompleteEventResetUse,
CssRootPath:this.CssRootPath,CustomWebFileColor:this.CustomWebFileColor,DefaultDownloadPath:this.DefaultDownloadPath,DevelopLangage:this.DevelopLangage,DisableAlertMessage:{Duplication:this.Duplication,DeleteUnchosen:this.DeleteUnchosen,DownloadUnchosen:this.DownloadUnchosen,OpenUnchosen:this.OpenUnchosen,DisableDeleteConfirm:this.DisableDeleteConfirm},DownloadHandlerUrl:this.DownloadHandlerUrl,ExtensionAllowOrLimit:this.ExtensionAllowOrLimit,ExtensionArr:this.ExtensionArr,FileMoveContextMenu:this.FileMoveContextMenu,
InterworkingModuleSecond:"N",FileNameRule:this.FileNameRule,FolderNameRule:this.FolderNameRule,HandlerUrl:this.HandlerUrl,HeaderBar:this.HeaderBar,HideContextMenu:this.HideContextMenu,HideListInfo:this.HideListInfo,ImgPreView:this.ImgPreView,ImgPreViewWidth:this.ImgPreViewWidth,ImgPreViewHeight:this.ImgPreViewHeight,InitXml:this.InitXml,InitServerXml:this.InitServerXml,RunTimes:this.RunTimes,Lang:this.Lang,LargeFile:{Size:this.LargeFileSize,MaxCount:this.LargeFileMaxCount,MaxTotalSize:this.LargeFileMaxTotalSize,
Text:this.LargeFileText,Color:this.LargeFileColor},ListViewDbclick:this.ListViewDbclick,ListViewDragAndDrop:this.ListViewDragAndDrop,MaxOneFileSize:this.MaxOneFileSize,MaxTotalFileCount:this.MaxTotalFileCount,MaxTotalFileSize:this.MaxTotalFileSize,Mode:this.Mode,MultiFileSelect:this.MultiFileSelect,RemoveContextItem:this.RemoveContextItem,ResumeDownload:this.ResumeDownload,ResumeUpload:this.ResumeUpload,SilentUpload:this.SilentUpload,SizeColumnWidth:this.SizeColumnWidth,SkinName:this.SkinName,StatusBar:this.StatusBar,
InterworkingModuleThird:"N",StatusBarShowLimit:this.StatusBarShowLimit,StatusBarShowStatus:this.StatusBarShowStatus,Timeout:this.Timeout,UploadTransferWindow:{View:this.UploadTransferWindowView},UseAddEvent:this.UseAddEvent,UseAfterAddEndTimeEvent:this.UseAfterAddEndTimeEvent,UseAfterAddEvent:this.UseAfterAddEvent,UseDeleteEvent:this.UseDeleteEvent,UseDownloadEvent:this.UseDownloadEvent,UseUploadingCancelEvent:this.UseUploadingCancelEvent,UseFileSort:this.UseFileSort,FileSortField:this.FileSortField,
FileSortAscDesc:this.FileSortAscDesc,AutoSort:this.AutoSort,UseOpenEvent:this.UseOpenEvent,UserMessage:{Edit:this.UserMessageEdit,View:this.UserMessageView},ViewerHandlerUrl:this.ViewerHandlerUrl,Views:this.Views,ImageQuality:{Quality:this.ImageQualityQuality,WorkerCount:this.ImageQualityWorkerCount,Extension:this.ImageQualityExtension,ChangedExtension:this.ImageQualityChangedExtension,OverFileSize:this.ImageQualityOverFileSize},ShowEditAlign:this.ShowEditAlign,ShowViewAlign:this.ShowViewAlign,ZIndex:this.ZIndex})};
_pRaonkUpload.on_destroy_contents=function(){try{RAONKUPLOAD.raonkUploadNexacroObject.removeItem(this.uploadID),RAONKUPLOAD.Destroy(this.uploadID)}catch(a){}};_pRaonkUpload.on_update_position=function(a,b){for(var c=this._child_list,e=c?c.length:0,d=0;d<e;d++){var f=c[d];f._isPopupVisible&&f._isPopupVisible()&&f._closePopup&&f._closePopup()}childe_list=null;nexacro.Div.prototype.on_update_position.call(this,a,b);a&&RAONKUPLOAD.IsLoadedUploadEx(this.uploadID)&&(c=this.getElement(),this.SetSize("100%",
c.height))};_pRaonkUpload.set_AllowedZeroFileSize=function(a){a!=this.AllowedZeroFileSize&&(this.AllowedZeroFileSize=a)};_pRaonkUpload.set_AutomaticConnection=function(a){a!=this.AutomaticConnection&&(this.AutomaticConnection=a)};_pRaonkUpload.set_BorderStyle=function(a){a!=this.BorderStyle&&(this.BorderStyle=a)};_pRaonkUpload.set_ButtonBarEdit=function(a){a!=this.ButtonBarEdit&&(this.ButtonBarEdit=a)};_pRaonkUpload.set_ButtonBarView=function(a){a!=this.ButtonBarView&&(this.ButtonBarView=a)};_pRaonkUpload.set_ButtonBarPosition=
function(a){a!=this.ButtonBarPosition&&(this.ButtonBarPosition=a)};_pRaonkUpload.set_CompleteEventResetUse=function(a){a!=this.CompleteEventResetUse&&(this.CompleteEventResetUse=a)};_pRaonkUpload.set_CssRootPath=function(a){a!=this.CssRootPath&&(this.CssRootPath=a)};_pRaonkUpload.set_CustomWebFileColor=function(a){a!=this.CustomWebFileColor&&(this.CustomWebFileColor=a)};_pRaonkUpload.set_DefaultDownloadPath=function(a){a!=this.DefaultDownloadPath&&(this.DefaultDownloadPath=a)};_pRaonkUpload.set_DevelopLangage=
function(a){a!=this.DevelopLangage&&(this.DevelopLangage=a)};_pRaonkUpload.set_DisableDeleteConfirm=function(a){a!=this.DisableDeleteConfirm&&(this.DisableDeleteConfirm=a)};_pRaonkUpload.set_Duplication=function(a){a!=this.Duplication&&(this.Duplication=a)};_pRaonkUpload.set_DeleteUnchosen=function(a){a!=this.DeleteUnchosen&&(this.DeleteUnchosen=a)};_pRaonkUpload.set_DownloadUnchosen=function(a){a!=this.DownloadUnchosen&&(this.DownloadUnchosen=a)};_pRaonkUpload.set_OpenUnchosen=function(a){a!=this.OpenUnchosen&&
(this.OpenUnchosen=a)};_pRaonkUpload.set_DownloadHandlerUrl=function(a){a!=this.DownloadHandlerUrl&&(this.DownloadHandlerUrl=a)};_pRaonkUpload.set_ExtensionAllowOrLimit=function(a){a!=this.ExtensionAllowOrLimit&&(this.ExtensionAllowOrLimit=a)};_pRaonkUpload.set_ExtensionArr=function(a){a!=this.ExtensionArr&&(this.ExtensionArr=a)};_pRaonkUpload.set_FileMoveContextMenu=function(a){a!=this.FileMoveContextMenu&&(this.FileMoveContextMenu=a)};_pRaonkUpload.set_FileNameRule=function(a){a!=this.FileNameRule&&
(this.FileNameRule=a)};_pRaonkUpload.set_FolderNameRule=function(a){a!=this.FolderNameRule&&(this.FolderNameRule=a)};_pRaonkUpload.set_HandlerUrl=function(a){a!=this.HandlerUrl&&(this.HandlerUrl=a)};_pRaonkUpload.set_HeaderBar=function(a){a!=this.HeaderBar&&(this.HeaderBar=a)};_pRaonkUpload.set_HideContextMenu=function(a){a!=this.HideContextMenu&&(this.HideContextMenu=a)};_pRaonkUpload.set_HideListInfo=function(a){a!=this.HideListInfo&&(this.HideListInfo=a)};_pRaonkUpload.set_ImgPreView=function(a){a!=
this.ImgPreView&&(this.ImgPreView=a)};_pRaonkUpload.set_ImgPreViewWidth=function(a){a!=this.ImgPreViewWidth&&(this.ImgPreViewWidth=a)};_pRaonkUpload.set_ImgPreViewHeight=function(a){a!=this.ImgPreViewHeight&&(this.ImgPreViewHeight=a)};_pRaonkUpload.set_InitXml=function(a){a!=this.InitXml&&(this.InitXml=a)};_pRaonkUpload.set_InitServerXml=function(a){a!=this.InitServerXml&&(this.InitServerXml=a)};_pRaonkUpload.set_RunTimes=function(a){a!=this.RunTimes&&(this.RunTimes=a)};_pRaonkUpload.set_Lang=function(a){a!=
this.Lang&&(this.Lang=a)};_pRaonkUpload.set_LargeFileSize=function(a){a!=this.LargeFileSize&&(this.LargeFileSize=a)};_pRaonkUpload.set_LargeFileMaxCount=function(a){a!=this.LargeFileMaxCount&&(this.LargeFileMaxCount=a)};_pRaonkUpload.set_LargeFileMaxTotalSize=function(a){a!=this.LargeFileMaxTotalSize&&(this.LargeFileMaxTotalSize=a)};_pRaonkUpload.set_LargeFileText=function(a){a!=this.LargeFileText&&(this.LargeFileText=a)};_pRaonkUpload.set_LargeFileColor=function(a){a!=this.LargeFileColor&&(this.LargeFileColor=
a)};_pRaonkUpload.set_ListViewDbclick=function(a){a!=this.ListViewDbclick&&(this.ListViewDbclick=a)};_pRaonkUpload.set_ListViewDragAndDrop=function(a){a!=this.ListViewDragAndDrop&&(this.ListViewDragAndDrop=a)};_pRaonkUpload.set_MaxOneFileSize=function(a){a!=this.MaxOneFileSize&&(this.MaxOneFileSize=a)};_pRaonkUpload.set_MaxTotalFileCount=function(a){a!=this.MaxTotalFileCount&&(this.MaxTotalFileCount=a)};_pRaonkUpload.set_MaxTotalFileSize=function(a){a!=this.MaxTotalFileSize&&(this.MaxTotalFileSize=
a)};_pRaonkUpload.set_Mode=function(a){a!=this.Mode&&(this.Mode=a)};_pRaonkUpload.set_MultiFileSelect=function(a){a!=this.MultiFileSelect&&(this.MultiFileSelect=a)};_pRaonkUpload.set_RemoveContextItem=function(a){a!=this.RemoveContextItem&&(this.RemoveContextItem=a)};_pRaonkUpload.set_ResumeDownload=function(a){a!=this.ResumeDownload&&(this.ResumeDownload=a)};_pRaonkUpload.set_ResumeUpload=function(a){a!=this.ResumeUpload&&(this.ResumeUpload=a)};_pRaonkUpload.set_SilentUpload=function(a){a!=this.SilentUpload&&
(this.SilentUpload=a)};_pRaonkUpload.set_SizeColumnWidth=function(a){a!=this.SizeColumnWidth&&(this.SizeColumnWidth=a)};_pRaonkUpload.set_SkinName=function(a){a!=this.SkinName&&(this.SkinName=a)};_pRaonkUpload.set_StatusBar=function(a){a!=this.StatusBar&&(this.StatusBar=a)};_pRaonkUpload.set_StatusBarShowLimit=function(a){a!=this.StatusBarShowLimit&&(this.StatusBarShowLimit=a)};_pRaonkUpload.set_StatusBarShowStatus=function(a){a!=this.StatusBarShowStatus&&(this.StatusBarShowStatus=a)};_pRaonkUpload.set_Timeout=
function(a){a!=this.Timeout&&(this.Timeout=a)};_pRaonkUpload.set_UploadTransferWindowView=function(a){a!=this.UploadTransferWindowView&&(this.UploadTransferWindowView=a)};_pRaonkUpload.set_UseAddEvent=function(a){a!=this.UseAddEvent&&(this.UseAddEvent=a)};_pRaonkUpload.set_UseAfterAddEndTimeEvent=function(a){a!=this.UseAfterAddEndTimeEvent&&(this.UseAfterAddEndTimeEvent=a)};_pRaonkUpload.set_UseAfterAddEvent=function(a){a!=this.UseAfterAddEvent&&(this.UseAfterAddEvent=a)};_pRaonkUpload.set_UseDeleteEvent=
function(a){a!=this.UseDeleteEvent&&(this.UseDeleteEvent=a)};_pRaonkUpload.set_UseDownloadEvent=function(a){a!=this.UseDownloadEvent&&(this.UseDownloadEvent=a)};_pRaonkUpload.set_UseUploadingCancelEvent=function(a){a!=this.UseUploadingCancelEvent&&(this.UseUploadingCancelEvent=a)};_pRaonkUpload.set_UseFileSort=function(a){a!=this.UseFileSort&&(this.UseFileSort=a)};_pRaonkUpload.set_FileSortField=function(a){a!=this.FileSortField&&(this.FileSortField=a)};_pRaonkUpload.set_FileSortAscDesc=function(a){a!=
this.FileSortAscDesc&&(this.FileSortAscDesc=a)};_pRaonkUpload.set_AutoSort=function(a){a!=this.AutoSort&&(this.AutoSort=a)};_pRaonkUpload.set_UseOpenEvent=function(a){a!=this.UseOpenEvent&&(this.UseOpenEvent=a)};_pRaonkUpload.set_UserMessageEdit=function(a){a!=this.UserMessageEdit&&(this.UserMessageEdit=a)};_pRaonkUpload.set_UserMessageView=function(a){a!=this.UserMessageView&&(this.UserMessageView=a)};_pRaonkUpload.set_ViewerHandlerUrl=function(a){a!=this.ViewerHandlerUrl&&(this.ViewerHandlerUrl=
a)};_pRaonkUpload.set_Views=function(a){a!=this.Views&&(this.Views=a)};_pRaonkUpload.set_ImageQualityQuality=function(a){a!=this.ImageQualityQuality&&(this.ImageQualityQuality=a)};_pRaonkUpload.set_ImageQualityWorkerCount=function(a){a!=this.ImageQualityWorkerCount&&(this.ImageQualityWorkerCount=a)};_pRaonkUpload.set_ImageQualityExtension=function(a){a!=this.ImageQualityExtension&&(this.ImageQualityExtension=a)};_pRaonkUpload.set_ImageQualityChangedExtension=function(a){a!=this.ImageQualityChangedExtension&&
(this.ImageQualityChangedExtension=a)};_pRaonkUpload.set_ImageQualityOverFileSize=function(a){a!=this.ImageQualityOverFileSize&&(this.ImageQualityOverFileSize=a)};_pRaonkUpload.set_ShowEditAlign=function(a){a!=this.ShowEditAlign&&(this.ShowEditAlign=a)};_pRaonkUpload.set_ShowViewAlign=function(a){a!=this.ShowViewAlign&&(this.ShowViewAlign=a)};_pRaonkUpload.set_ZIndex=function(a){a!=this.ZIndex&&(this.ZIndex=a)};_pRaonkUpload.AddFormData=function(a,b){RAONKUPLOAD.AddFormData(a,b,this.uploadID)};_pRaonkUpload.AddHttpHeader=
function(a,b){RAONKUPLOAD.AddHttpHeader(a,b,this.uploadID)};_pRaonkUpload.AddLocalFileDirectly=function(a){RAONKUPLOAD.AddLocalFileDirectly(a,this.uploadID)};_pRaonkUpload.AddUploadedFile=function(a,b,c,e,d){RAONKUPLOAD.AddUploadedFile(a,b,c,e,d,this.uploadID)};_pRaonkUpload.AddUploadedFileEw=function(a,b,c,e,d){RAONKUPLOAD.AddUploadedFileEw(a,b,c,e,d,this.uploadID)};_pRaonkUpload.AddUploadedFileWithGetFileSize=function(a,b,c,e,d){RAONKUPLOAD.AddUploadedFileWithGetFileSize(a,b,c,e,d,this.uploadID)};
_pRaonkUpload.AddUploadedFileEx=function(a,b,c,e,d,f){RAONKUPLOAD.AddUploadedFileEx(a,b,c,e,d,f,this.uploadID)};_pRaonkUpload.AddUploadedFileExWithGetFileSize=function(a,b,c,e,d,f){RAONKUPLOAD.AddUploadedFileExWithGetFileSize(a,b,c,e,d,f,this.uploadID)};_pRaonkUpload.DeleteAllFile=function(){RAONKUPLOAD.DeleteAllFile(this.uploadID)};_pRaonkUpload.DeleteSelectedFile=function(){RAONKUPLOAD.DeleteSelectedFile(this.uploadID)};_pRaonkUpload.DownloadFile=function(){RAONKUPLOAD.DownloadFile(this.uploadID)};
_pRaonkUpload.DownloadAllFile=function(){RAONKUPLOAD.DownloadAllFile(this.uploadID)};_pRaonkUpload.GetDeleteList=function(a){return RAONKUPLOAD.GetDeleteList(a,this.uploadID)};_pRaonkUpload.GetListInfo=function(a){return RAONKUPLOAD.GetListInfo(a,this.uploadID)};_pRaonkUpload.GetNewUploadList=function(a){return RAONKUPLOAD.GetNewUploadList(a,this.uploadID)};_pRaonkUpload.GetTotalFileCount=function(){return RAONKUPLOAD.GetTotalFileCount(this.uploadID)};_pRaonkUpload.GetTotalFileSize=function(){return RAONKUPLOAD.GetTotalFileSize(this.uploadID)};
_pRaonkUpload.GetUploadCompleteState=function(){return RAONKUPLOAD.GetUploadCompleteState(this.uploadID)};_pRaonkUpload.Hidden=function(){RAONKUPLOAD.Hidden(this.uploadID)};_pRaonkUpload.MoveFirstFile=function(){RAONKUPLOAD.MoveFirstFile(this.uploadID)};_pRaonkUpload.MoveForwardFile=function(){RAONKUPLOAD.MoveForwardFile(this.uploadID)};_pRaonkUpload.MoveBackwardFile=function(){RAONKUPLOAD.MoveBackwardFile(this.uploadID)};_pRaonkUpload.MoveEndFile=function(){RAONKUPLOAD.MoveEndFile(this.uploadID)};
_pRaonkUpload.OpenFileDialog=function(){RAONKUPLOAD.OpenFileDialog(this.uploadID)};_pRaonkUpload.OpenSelectedFile=function(){RAONKUPLOAD.OpenSelectedFile(this.uploadID)};_pRaonkUpload.PrintSelectedFile=function(){RAONKUPLOAD.PrintSelectedFile(this.uploadID)};_pRaonkUpload.ResetUpload=function(){RAONKUPLOAD.ResetUpload(this.uploadID)};_pRaonkUpload.ResetUploadCompleteState=function(){RAONKUPLOAD.ResetUploadCompleteState(this.uploadID)};_pRaonkUpload.SaveAndFolderOpen=function(){RAONKUPLOAD.SaveAndFolderOpen(this.uploadID)};
_pRaonkUpload.SaveAndOpen=function(){RAONKUPLOAD.SaveAndOpen(this.uploadID)};_pRaonkUpload.SetAllowOrLimitExtension=function(a,b){RAONKUPLOAD.SetAllowOrLimitExtension(a,b,this.uploadID)};_pRaonkUpload.SetConfig=function(a,b){RAONKUPLOAD.SetConfig(a,b,this.uploadID)};_pRaonkUpload.SetDefaultSavePath=function(a){return RAONKUPLOAD.SetDefaultSavePath(a,this.uploadID)};_pRaonkUpload.SetFileCustomValue=function(a,b){return RAONKUPLOAD.SetFileCustomValue(a,b,this.uploadID)};_pRaonkUpload.SetMaxOneFileSize=
function(a){RAONKUPLOAD.SetMaxOneFileSize(a,this.uploadID)};_pRaonkUpload.SetMaxTotalFileSize=function(a){RAONKUPLOAD.SetMaxTotalFileSize(a,this.uploadID)};_pRaonkUpload.SetMaxTotalFileCount=function(a){RAONKUPLOAD.SetMaxTotalFileCount(a,this.uploadID)};_pRaonkUpload.SetSelectFile=function(a,b){RAONKUPLOAD.SetSelectFile(a,b,this.uploadID)};_pRaonkUpload.SetSelectFile=function(a,b){RAONKUPLOAD.SetSelectFile(a,b,this.uploadID)};_pRaonkUpload.SetSize=function(a,b){RAONKUPLOAD.SetSize(a+"",b+"",this.uploadID)};
_pRaonkUpload.SetUploadMode=function(a){RAONKUPLOAD.SetUploadMode(a,this.uploadID)};_pRaonkUpload.Show=function(){RAONKUPLOAD.Show(this.uploadID)};_pRaonkUpload.Transfer=function(){RAONKUPLOAD.Transfer(this.uploadID)};_pRaonkUpload.Destroy=function(){RAONKUPLOAD.Destroy(this.uploadID)};_pRaonkUpload.SetButtonList=function(a){RAONKUPLOAD.SetButtonList(a,this.uploadID)};_pRaonkUpload._RAONKUPLOAD_AfterAddAllFile=function(a){if(this.RAONKUPLOAD_AfterAddAllFile&&this.RAONKUPLOAD_AfterAddAllFile._has_handlers)return this.RAONKUPLOAD_AfterAddAllFile._fireEvent(this)};
_pRaonkUpload._RAONKUPLOAD_AfterAddFile=function(a,b){if(this.RAONKUPLOAD_AfterAddFile&&this.RAONKUPLOAD_AfterAddFile._has_handlers)return this.RAONKUPLOAD_AfterAddFile._fireEvent(this,b)};_pRaonkUpload._RAONKUPLOAD_BeforeAddFile=function(a,b){return this.RAONKUPLOAD_BeforeAddFile&&this.RAONKUPLOAD_BeforeAddFile._has_handlers?this.RAONKUPLOAD_BeforeAddFile._fireEvent(this,b):!0};_pRaonkUpload._RAONKUPLOAD_BeforeDeleteFile=function(a,b){return this.RAONKUPLOAD_BeforeDeleteFile&&this.RAONKUPLOAD_BeforeDeleteFile._has_handlers?
this.RAONKUPLOAD_BeforeDeleteFile._fireEvent(this,b):!0};_pRaonkUpload._RAONKUPLOAD_BeforeDownloadFile=function(a,b){return this.RAONKUPLOAD_BeforeDownloadFile&&this.RAONKUPLOAD_BeforeDownloadFile._has_handlers?this.RAONKUPLOAD_BeforeDownloadFile._fireEvent(this,b):!0};_pRaonkUpload._RAONKUPLOAD_BeforeOpenFile=function(a,b){return this.RAONKUPLOAD_BeforeOpenFile&&this.RAONKUPLOAD_BeforeOpenFile._has_handlers?this.RAONKUPLOAD_BeforeOpenFile._fireEvent(this,b):!0};_pRaonkUpload._RAONKUPLOAD_BeforeUpload=
function(a,b){return this.RAONKUPLOAD_BeforeUpload&&this.RAONKUPLOAD_BeforeUpload._has_handlers?this.RAONKUPLOAD_BeforeUpload._fireEvent(this,b):!0};_pRaonkUpload._RAONKUPLOAD_CreationComplete=function(a,b){if(this.RAONKUPLOAD_CreationComplete&&this.RAONKUPLOAD_CreationComplete._has_handlers)return this.RAONKUPLOAD_CreationComplete._fireEvent(this,b)};_pRaonkUpload._RAONKUPLOAD_BeforeCreation=function(){if(this.RAONKUPLOAD_BeforeCreation&&this.RAONKUPLOAD_BeforeCreation._has_handlers)return this.RAONKUPLOAD_BeforeCreation._fireEvent(this)};
_pRaonkUpload._RAONKUPLOAD_DownloadCompleteFile=function(a,b){if(this.RAONKUPLOAD_DownloadCompleteFile&&this.RAONKUPLOAD_DownloadCompleteFile._has_handlers)return this.RAONKUPLOAD_DownloadCompleteFile._fireEvent(this,b)};_pRaonkUpload._RAONKUPLOAD_DownloadCompleteAllFile=function(a,b){if(this.RAONKUPLOAD_DownloadCompleteAllFile&&this.RAONKUPLOAD_DownloadCompleteAllFile._has_handlers)return this.RAONKUPLOAD_DownloadCompleteAllFile._fireEvent(this,b)};_pRaonkUpload._RAONKUPLOAD_OnError=function(a,b){if(this.RAONKUPLOAD_OnError&&
this.RAONKUPLOAD_OnError._has_handlers)return this.RAONKUPLOAD_OnError._fireEvent(this,b)};_pRaonkUpload._RAONKUPLOAD_UploadComplete=function(a){if(this.RAONKUPLOAD_UploadComplete&&this.RAONKUPLOAD_UploadComplete._has_handlers)return this.RAONKUPLOAD_UploadComplete._fireEvent(this)};_pRaonkUpload._RAONKUPLOAD_UploadingCancel=function(a,b){if(this.RAONKUPLOAD_UploadingCancel&&this.RAONKUPLOAD_UploadingCancel._has_handlers)return this.RAONKUPLOAD_UploadingCancel._fireEvent(this,b)};_pRaonkUpload._RAONKUPLOAD_SelectItem=
function(a,b){if(this.RAONKUPLOAD_SelectItem&&this.RAONKUPLOAD_SelectItem._has_handlers)return this.RAONKUPLOAD_SelectItem._fireEvent(this,b)}};