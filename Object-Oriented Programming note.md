# 객체 지향 프로그래밍 입문

## Introduction

**캡슐화와 추상화에 대해서 중점적으로 설명하겠다.**

### 선수 지식

- private, public
- 클래스, 추상 클래스, 인터페이스
- 추상 메서드
- 상속(extends, implements)
- 오버라이딩(재정의)

### 비용

버젼이 올라갈 수록 코드 한줄당 작성 비용이 늘어난다.

### 주요 원인

- 코드 분석 시간 증가
- 코드 변경 시간 증가

### 소프트웨어 가치 : 변화

**소프트웨어를 유지보수 한다는 것은 이전처럼 동작하는게 아니라, 변화하는 세계에서 여전히 유용해야 한다.**

### 비용과 변화

- 낮은 비용으로 변화할 수 있어야 함
- 이를 위한 방법
   - 패러다임
      - 객체 지향, 함수형, 리액티브, ...
   - 코드, 설계, 아키텍처
      - DRY, TDD, SOLID, DDD, ...
      - 클린 아키텍처, MSA, ...
   - 업무 프로세스/문화
      - 애자일, DevOps, ...

### 객체 지향과 비용

**캡슐화 + 다형성(추상화)**

---

## Object(객체)

### 절차 지향

**데이터를 여러 프로시저가 공유하는 방식**

 **시간이 흘러갈 수록 데이터를 공유하는 방식은 구조가 복잡해지고 유지보수를 어렵게 한다.**

### 절차 지향과 비용

데이터를 여러 프로시저가 사용하는 절차 지향적인 언어는 데이터를 자꾸 복사하게 되고 코드를 자꾸 복사하게 되는 비효율적인 현상이 발생한다.

### 객체 지향 (Object-Oriented)

**객체 지향은 데이터와 프로시저를 객체라는 단위로 묶는다.**

특정 객체가 가지고 있는 데이터를 그 객체의 프로시저만 접근할 수 있도록 한다.

다른 객체에서는 그 객체의 데이터(private 필드)에 바로 접근할 수 없다.

**객체는 프로시저를 이용해서 외부에 데이터를 제공한다. (프로시저를 호출하는 방식)**

### 객체란

-  **객체의 핵심 -> 기능 제공**
   - 객체는 제공하는 기능으로 정의
      - 내부적으로 가진 필드(데이터)로 정의하지 않음
- 예 : 회원 객체
   - 암호 변경하기 기능
   - 차단 여부 확인 하기 기능
- 예 : 소리 제어기
   - 소리 크기 증가하기 기능
   - 소리 크기 감소하기 기능

### 기능 명세

- **메서드(오퍼레이션)를 이용해서 기능 명세**

   - 이름, 파라미터, 결과로 구성

   ```java
   public class VolumeController{
     public void increase(int inc){
       ...
     }
     public void decrease(int dec){
       ...
     }
     public int volume(){
       ...
     }
   }
   
   public Member{
     public void changePassword(String curPw, String newPw){
       ...
     }
   }
   ```

### 객체와 객체

- 객체와 객체는 기능을 사용해서 연결

   - 기능 사용 = 메서드 호출

   ```java
   VolumnController volCont = new VolumnController();
   
   volCont.increase(4);
   
   volCont.decrease(3);
   
   int currentVol = volCont.volume();
   ```

### 용어: 메세지

- **<u>객체와 객체 상호 작용</u> : 메시지를 주고 받는다고 표현**
   - 메서드를 호출하는 메시지, 리턴하는 메시지, 익셉션 메시지

### 객체 (객체는 기능으로 정의한다 !!!! 꼭 기억하자)

```java
// 이 클래스에서 생성된 인스턴스는 객체가 아니라 데이터이다.
// 이 클래스에서 기능이 붙은 인스턴스를 객체라고 할 수 있다.
public class Member{
  private String name;
  private String id;
  
  public void setName(String name){
    this.name = name;
  }
  public String getName(){
    return name;
  }
  public void setId(String id){
    this.id = id;
  }
  public String getId(){
    return id;
  }
}
```

---

## 캡슐화(Encapsulation)

- **데이터 + 관련 기능 묶기**
- **객체가 기능을 어떻게 구현했는지 외부에 감추는 것**
   - 구현에 사용된 데이터의 상세 내용을 외부에 감춤
- **정보 은닉(Information Hiding) 의미 포함**
- **외부에 영향 없이 객체 내부 구현 변경 가능**

### 캡슐화하지 않으면

요구사항의 변화가 데이터 구조/사용에 변화를 발생시킴 -> 데이터를 사용하는 코드의 수정 발생

### 캡슐화하면

- **기능을 제공하고 구현 상세를 감춤**

   ```java
   public class Account{
     private Membership membership;
     private Date expDate;
     public boolean hasRegularPermission(){
       return membership == REGULAR && expDate.isAfter(now())
     }
   }
   
   if(acc.hasRegularPermission()){
   	...정회원 기능
   }
   
   //요구사항 변화 => 내부 구현만 변경
   public class Account{
     private Membership membership;
     private Date expDate;
     public boolean hasRegularPermission(){
       return membership == REGULAR && 
         (expDate.isAfter(now()) ||
          (
          	serviceDate.isBefore(fiveYearAgo()) &&
            addMonth(expDate).isAfter(now())
          )
         );
     }
   }
   
   if(acc.hasRegularPermission()){
   	...정회원 기능
   }
   
   ```

- **연쇄적인 변경 전파를 최소화**

   - 요구사항의 변화가 내부 구현을 변경 => 캡슐화된 기능을 사용하는 코드 영향 최소화

- **캡슐화 시도 => 기능에 대한 (의도) 이해를 높임**

### 캡슐화를 위한 규칙

- **Tell, Don't Ask**

   - 데이터 달라 하지 말고 해달라고 하기

   ```java
   if(acc.getMembership() == REGULAR){
     ... 정회원 기능
   }
   
   // 캡슐화
   if(acc.hasRegularPermission()){
     ... 정회원 기능
   }
   ```

- **Demeter's Law**

   - 메서드에서 생성한 객체의 메서드만 호출
   - 파라미터로 받은 객체의 메서드만 호출
   - 필드로 참조하는 객체의 메서드만 호출

   ```java
   acc.getExpDate().isAfter(now);
   // Demeter's Law 적용
   acc.isExpired();
   
   //////////////////////////////////
   Date date = acc.getExpDate();
   date.isAfter(now);
   //Demeter's Law 적용
   acc.isValid(now);
   ```

### 정리

**캡슐화 : 기능의 구현을 외부에 감춤**

**캡슐화를 통해 기능을 사용하는 코드에 영향을 주지 않고 (또는 최소화) 내부 구현을 변경할 수 있는 유연함**

---

## 캡슐화 연습

캡슐화 연습1 (캡슐화 이전 코드)

```java
public AuthResult authenticate(String id, String pw){
  Member mem = findOne(id);
  if ( mem == null ) return AuthResult.NO_MATCH;
  
  if( mem.getVerificationEmailStatus() != 2){	// Tell, Dont' Ask의 원칙을 통해 캡슐화 가능한 코드
    return AuthResult.NO_EMAIL_VERIFIED;
  }
  if(passwordEncoder.isPasswordValid(mem.getPassword(), pw, mem.getID())){
    return AuthResult.SUCCESS;
  }
  return AuthResult.NO_MATCH;
}
```

캡슐화 연습1 (캡슐화 완성 코드)

```java
public class Member{
  private int verificationEmailStatus;
  
  public boolean isEmailVerified(){	//캡슐화를 진행한 코드
    return verificationEmailStatus == 2;
  }
}

public AuthResult authenticate(String id, String pw){
  Member mem = findOne(id);
  if ( mem == null ) return AuthResult.NO_MATCH;
  
  if(!mem.isEmailVerified()){	// 캡슐화를 진행한 코드
    return AuthResult.NO_EMAIL_VERIFIED;
  }
  if(passwordEncoder.isPasswordValid(mem.getPassword(), pw, mem.getID())){
    return AuthResult.SUCCESS;
  }
  return AuthResult.NO_MATCH;
}
```

캡슐화 연습 2 ( 캡슐화 이전 코드 )

```java
public class Rental{
  private Movie movie;
  private int daysRented;
  
  public int getFrequentRenterPoints(){
    if(movie.getPriceCode() == Movie.NEW_RELEASE && daysRented > 1)
      return 2;
    else
      return 1;
  }
	...
}

public class Movie{
  public static int REGULAR = 0;
  public static int NEW_RELEASE = 1;
  private int priceCode;
  
  public int getPriceCode(){
    return priceCode;
  }
  ...
}
```

캡슐화 연습 2 (캡슐화 완성 코드)

```java
public class Rental{
  private Movie movie;
  private int daysRented;
  
  public int getFrequentRenterPoints(){
		return movie.getFrequentRenterPoints(dayRented);
  }
	...
}

public class Movie{
  public static int REGULAR = 0;
  public static int NEW_RELEASE = 1;
  private int priceCode;
  
  public int getFrequentRenterPoints(int daysRented){
    if(priceCode == NEW_RELEASE && daysRented >1)
      return 2;
    else
      return 1;
  }
  ...
}
```

캡슐화 연습 3 (캡슐화 이전 코드)

```java
public class Timer{
  public long startTime;
  public long stopTime;
}

Timer t = new Timer();
t.startTime = System.currentTimeMillis();

...
  

t.stopTime = System.currentTimeMillis();

long elaspedTime = t.stopTime - t.startTime;
```

캡슐화 연습 3 (캡슐화 완성 코드)

```java
public class Timer{
  private long startTime;
  private long stopTime;
  
  public void start(){
    this.startTime = System.currentTimeMillis();
  }
  
  public void stop(){
    this.stopTime = System.currentTimeMillis();
  }
  
  public long elapsedTime(TimeUnit unit){
    switch(unit){
      case MILLISECOND:
        return stopTime - startTime;
     	...
    }
  }
}

Timer t = new Timer();
t.start();



t.stop();
long time = t.elapsedTime(MILLISECOND);
```

캡슐화 연습 4 (캡슐화 이전 코드)

```java
public void verifyEmail(String token){
  Member mem = findByToken(token);
  if ( mem == null ) throw new BadTokenException();
  if(mem.getVerificationEmailStatus() == 2){	// Tell, Don't Ask에 의해서 변경가능
    throw new AlreadyVerifiedException();
  } else {
    mem.setVerificationEmailStatus(2);				// 무언가가 캡슐화가 필요한 것 같다.
  }
  // ... 수정사항 DB 반영
}
```

캡슐화 연습 4 (캡슐화 완성 코드)

```java
public class Member{
  private int verificationEmailStatus;
  
  public void verifyEmail(){
    if(isEmailVerified())
      throw new AlreadyVerifiedException();
    else
      this.verificationEmailStatus =2;
  }
  
  public boolean isEmailVerified(){
    return verificationEmailStatus == 2;
  }
}


public void verifyEmail(String token){
  Member mem = findByToken(token);
  if ( mem == null ) throw new BadTokenException();

  mem.verifyEmail();
  
  // ... 수정사항 DB 반영
}
```

---

## 다형성과 추상화

### 다형성(polymorphism)

- **여러(poly) 모습(morph)을 갖는 것**
- **객체 지향에서는 한 객체가 여러 타입을 갖는 것**
   - 즉 한 객체가 여러 타입의 기능을 제공
   - 타입 상속으로 다형성 구현
      - 하위 타입은 상위 타입도 됨

### 추상화(Abstraction)

- **데이터나 프로세스 등을 의미가 비슷한 개념이나 의미 있는 표현으로 정의하는 과정**
- **두 가지 방식의 추상화**
   - 특정한 성질, 공통 성질 (일반화)

### 타입 추상화(Type Abstraction)

- **여러 구현 클래스를 대표하는 상위 타입 도출**
   - 흔히 인터페이스 타입으로 추상화
   - 추상화 타입과 구현은 타입 상속으로 연결

### 추상 타입 사용

- 추상 타입을 이용한 프로그래밍

   ```java
   Noifier notifier = getNotifier(...);
   notifier.notify(someNoti);
   ```

- 추상 타입은 구현을 감춤

   - 기능의 구현이 아닌 의도를 더 잘 드러냄

### 추상 타입 사용에 따른 이점 : 유연함

- 사용할 대상 접근도 추상화

### 추상화는 의존 대상이 변경하는 시점에

- 추상화 -> 추상 타입 증가 -> 복잡도 증가

   - 아직 존재하지 않는 기능에 대한 이른 추상화는 주의: 잘못된 추상화 가능성, 복잡도만 증가
   - 실제 변경, 확장이 발생할 때 추상화 시도

   ```java
   // 기존 코드
   public class OrderService{
     private MailSender sender;
     
     public void order(...){
       // ...
       sender.send(message);
     }
   }
   // 기존 코드에 요구사항 추가
   public class OrderService{
     private MailSender sender;
     private SmsService smsService;
     
     public void order(...){
       // ...
       sender.send(message);
       // ...
       smsService.send(smsMsg);
     }
   }
   // 추상화
   public class OrderService{
     private Notifier notifier;
     
     public void order(...){
       // ...
       notifier.notify(noti);
     }
   }
   ```

### 추상화를 잘 하려면

- **구현을 한 이유가 무엇 때문인지 생각해야 함**

---

## 추상화 예시

### 예시

- 기능 예시
   - 클라우드 파일 통합 관리 기능 개발
   - 대상 클라우드
      - 드롭박스, 박스
   - 주요 기능
      - 각 클라우드의 파일 목록 조회, 다운로드, 업로드, 삭제, 검색

**추상화하지 않은 구현**

```java
public enum CloudId{
  DROPBOX,
  BOX;
}
////////////////////////////////
public class FileInfo{	//특정 파일 정보를 표현하기 위한 클래스
  private CloudId cloudId;
  private String fileId;
  private String name;
  private long length;
  
  //... get 메서드
}
///////////////////////////////
public class CloudFileManager{
  public List<FileInfo> getFileInfos(CloudId cloudId){	// 파일 목록을 제공하는 메소드
    if(cloudId == CloudId.DROPBOX){	// cloudId가 DROPBOX이면
      DropboxClient dc = ...;
      List<DBFile> dbFiles = db.getFiles();
      List<FileInfo> result = new ArrayList<>();	// FileInfo로 변환하는 코드
      for(DbFile dbFile : dbFiles){
        FileInfo fi = new FileInfo();
        fi.setCloudId(CloudId.DROPBOX);
        fi.setFileId(fi.getFileId());
        //...
        result.add(fi);
      }
      return result;
    } else if(cloudId == CloudId.BOX){	//CloudId가 BOX이면 실행하는 코드
      BoxService boxSvc = ...;
      //...
    }
  }
  	
  public void download(FileInfo file, File localTarget){	// 파일을 다운로드하는 메소드
    									// 파일정보					// 파일저장위치
    if(file.getCloudId() == CloudId.DROPBOX){ // 클라우드Id의 식별자에 따라 분기하는 메서드
      DropboxClient dc = ...;
      FileOutputStream out = new FileOutputStream(localTarget);	// 데이터를 저장할 변수
      dc.copy(file.getFileId(), out);	// local에 복사하는 코드
      out.close();
    } else if (file.getCloudId() == CloudId.Box){
      BoxService boxSvc = ...;
      InputStream is = boxSvc.getInputStream(file.getId());
      FileOutputStream out = new FileOutputStream(localTarget);
      CopyUtil.copy(is, out);
    }
  } 
  
  public FileInfo upload(File file, CloudId cid){ // 파일을 업로드하는 메소드 (분기는 계속)
    if ( cid == CloudId.DROPBOX){
      ...
    } else if ( cid == CloudId.BOX){
      ...
    }
  }
  
  // 지원하는 클라우드 추가 (S 클라우드, N 클라우드, D 클라우드)
  public List<FileInfo> getFileInfos(CloudId cloudId){	// 클라우드 추가 메서드
    if(cloudId == CloudID.DROPBOX){
      // ...
    } else if(cloudId == CloudId.BOX){
      // ...
    } else if(cloudId == CloudId.SCloud){
      // ...
    } else if(cloudId == CloudId.NCLOUD){
      // ...
    } else if(cloudId == CloudId.DCLOUD){
      // ...
    }
  }
  // download(), upload(), delete(), search()도 유사한 else-if 블록 추가
  // ...
  public FileInfo copy(FileInfo fileInfo, CloudId to){ // 클라우드 간 복사
    																		//fileInfo 에 해당하는 파일을 to에 해당하는 클라우드에 복사해라
    CloudId from = fileInfo.getClouId();
    if( to == CloudId.DROPBOX){
      DropBoxClient dbClient = ...;
      if( from == CloudId.BOX){
        dbClient.copyFromUrl("http://www.box.com/files/" + fileInfo.getFileId());
      }else if(from == CloudId.SCLOUD){
        ScloudClient sClient = ...;
        //...
      }//...
    }
    
  }
  // ...
}
```

추상화후 코드

```java
public class DropBoxFileSystem implements CloudFileSystem{
  private DropBoxClient dbClient = new DropBoxClient(...);
  
  @Override
  public List<CloudFile> getFiles(){
    List<DbFile> dbFiles = dbClient.getFiles();
    List<CloudFile> results = new ArrayList<>(dbFiles.size());
    for(DbFile file : dbFiles){
      DropBoxCloudFile cf = new DropBoxCloudFile(file, dbClient);
      results.add(cf);
    }
    return results;
  }
}

public class DropBoxCloudFile implements CloudFile{
  private DropBoxClient dbClient;
  private DbFile dbFile;
  
  public DropBoxCloutFile(DbFile dbFile, dbclient){
    this.dbFile = dbFile;
    this.dbClient = dbClient;
  }
  public String getId(){
    return dbFile.getId();
  }
  public boolean hasUrl(){
    return true;
  }
  public String getUrl(){
    return dbFile.getFileUrl();
  }
  public String getName(){
    return dbFile.getFileName();
  }
  public InputStream getInputStream(){
    return dbClient.createStreamOfFile(dbFile);
  }
  public void write(OutputStream out){
    //...
  }
  public void delete(){
    dbClient.deleteFile(dbFile.getId());
  }
  //...
}

public List<CloudFile> getFileInfos(CloudId cloudId){	//파일 목록
  CloudFileSystem fileSystem = CloudFileSystemFactory.getFileSystem(cloudId);
  return fileSystem.getFiles();
}

public void download(CloudFile file, File localTarget){	//파일 다운로드
  file.write(new FileOutputStream(localTarget));
}

public void copy(CloudFile file, CloudId target){	// 파일 복사 기능
  CloudFileSystem fileSystem = CloudFileSYstemFactory.getFileSystem(target);
  fileSystem.copyFrom(file);
}
```

### 추상화 결과

- 코드 수정없이 새로운 클라우드 지원 추가
- 이것이 바로 OCP(Open - Closed Principle) : 확장에는 열려 있음(Open for Extenstion), 수정엔 닫혀 있음(Closed for Modification)

### 개발 시간 증가 이유

- 코드 구조가 길어지고 복잡해짐
   - 중첩 if-else는 복잡도 배로 증가
   - if-else가 많을수록 진척 더딤 (신중 모드)
- 관련 코드가 여러 곳에 분산됨
- 결과적으로, 코드 가독성과 분석 속도 저하
   - 코드 추가에 따른 노동 시간 증가
   - 실수하기 쉽고 이루 인한 불필요한 디버깅 시간 증가































