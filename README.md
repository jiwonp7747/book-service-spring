# BOOK SWAP 프로젝트

## 프로젝트 소개 

## API 명세 
#### user
|Domain|Method|Url|Description|
|-----|-----|-----|-----|
|user|POST|/open-api/user|유저 회원가입|
|user|POST|/open-api/user/login|유저 로그인|
|user|GET|/api/user|유저 정보 가져오기|

#### post
|Domain|Method|Url|Description|
|-----|-----|-----|-----|
|post|POST|/api/post/register|글 등록하기|
|post|GET|/api/post/get-list|전체글 가져오기|
|post|GET|/api/post/get-list/user|해당 유저가 작성한 글 가져오기|

#### reply
|Domain|Method|Url|Description|
|-----|-----|-----|-----|
|reply|POST|/api/reply|교환 요청 댓글 작성|
|reply|GET|/api/reply/get-list|해당 게시글 댓글 리스트 가져오기|

#### chatroom
|Domain|Method|Url|Description|
|-----|-----|-----|-----|
|ChatRoom|POST|/api/chat-room|채팅방 생성하기|
|ChatRoom|GET|/api/chat-room/get-list|유저가 참여하고 있는 채팅방 목록 가져오기|

#### chatmessage
|Domain|Method|Url|Description|
|-----|-----|-----|-----|
|ChatMessage|POST|/api/chat-message/register|채팅방 입력하기|
|ChatMessage|GET|/api/chat-message/get-list|채팅방 채팅 리스트 가져오기|




