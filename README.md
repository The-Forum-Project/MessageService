# MessageService
Message service. 
TOOO: should add Authentication in controller. 区分一下visitor 和正常的user，如果是visitor就把userid设为-1 在create message里。 如果是正常user，就按照userid来。
GET的还没加authentication 需要去区分user 的role
PATCH status完成 把message 分了open/close 两个status
