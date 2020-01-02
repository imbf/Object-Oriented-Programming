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
public class DropBoxFileSystem implements CloudFileSystem{	//DROPBOX용 cloudFIleSystem
  private DropBoxClient dbClient = new DropBoxClient(...);
  
  @Override
  public List<CloudFile> getFiles(){	// 실제로는 요소가 CloudFile인 List를 리턴한다.
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

// BoxCloud추가를 위해서 Concrete Class를 추가한다.
```

### 추상화 결과

- **코드 수정없이 새로운 클라우드 지원 추가**
- **이것이 바로 OCP(Open - Closed Principle: 개방 폐쇄 원칙) : 확장에는 열려 있음(Open for Extenstion), 수정엔 닫혀 있음(Closed for Modification)** => 변경이나 확장하는 비용을 낮춰주게 한다.

### 개발 시간 증가 이유

- 코드 구조가 길어지고 복잡해짐
   - 중첩 if-else는 복잡도 배로 증가
   - if-else가 많을수록 진척 더딤 (신중 모드)
- 관련 코드가 여러 곳에 분산됨
- 결과적으로, 코드 가독성과 분석 속도 저하
   - 코드 추가에 따른 노동 시간 증가
   - 실수하기 쉽고 이루 인한 불필요한 디버깅 시간 증가

---

## 상속보다는 조립

### 상속과 재사용

- 상속은 상위 클래스의 기능을 재사용, 확장하는 방법으로 활용하나

### 상속을 통한 기능 재사용시 발생할 수 있는 단점

- **상위 클래스 변경 어려움**
   - 변경의 여파가 계층도를 따라 하위 클래스로 전파됨 -> 상위 클래스 변경 어려움
- **클래스 증가**
   - 무언가가 새로운 조합이 생길 때마다 클래스들이 증가된다.
- **상속 오용**

### 상속의 단점 해결 방법 -> 조립

- 조립(Composition)
   - 여러 객체를 묶어서 더 복잡한 기능을 제공
   - 보통 필드로 다른 객체를 참조하는 방식으로 조립 또는 객체를 필요 시점에 생성/구함

### 조립을 통한 기능 재사용

```java
public class Container extends ArrayList<Luggage>{	// 상속
  private int maxSize;
  private int currentSize;
  // ...
  public void put(Luggage lug){
    if(!canContain(lug))
      throw new NotEnoughSpaceException();
    super.add(lug);
    currentSize += lug.size();
  }
  // ...
}

// 조립을 통한 기능 재사용
public class Container{
  private int maxSize;
  private int currentSize;
  private List<Luggage> luggages = new ArrayList();	// 조립
  //...
  public void put(Luggage lug){
    if(!canContain(lug))
      throw new NotEnoughSpaceException();
    luggages.add(lug);
  }
}
```

### 상속보다는 조립(Composition over inheritance)

- 상속하기에 앞서 조립으로 풀 수 없는지 검토
- 진짜 하위 타입인 경우에만 상속 사용

---

## 기능과 책임 분리

### 기능 분해

- **기능은 하위 기능으로 분해**

### 기능을 누가 제공할 것인가?

- **기능은 곧 책임**
   - 분리한 각 기능을 알맞게 분배

### 하위 기능 사용

```java
// 본 기능을 여러개로 분리해서 기능을 조합해서 사용
public class ChangePasswordService{
  public Result changePassword(String id, String oldPw, String newPw){
  	Member mem = memberRepository.findOne(id);
    if(mem==null){
      return Result.NO_MEMBER;
    }
    try{
      mem.changePassword(oldPw newPw);
      return REsult.SUCCESS;
    } catch(BadPasswordException ex){
      return Result.BAD_PASSWORD;
    }
  }
  
}
```

### 큰 클래스, 큰 메서드

- **클래스나 메서드가 커지면 절차 지향의 문제 발생**
   - 큰 클래스 -> 많은 필드를 많은 메서드가 공유
   - 큰 메서드 -> 많은 변수를 많은 코드가 공유
   - 여러 기능이 한 클래스/메서드에 섞여 있을 가능성
- **책임에 따라 알맞은 객체로 코드 분리 필요**

### 책임 분배/분리 방법

- **패턴 적용**
   - 전형적인 역할 분리
      - 간단한 웹
         - 컨트롤러, 서비스, DAO
      - 복잡한 도메인
         - 엔티티, 밸류, 리포지토리, 도메인 서비스
      - AOP
         - Aspect(공통 기능)
      - GoF
         - 팩토리, 빌더, 전략, 템플릿 메서드, 프록시/데코레이터 등
- **계산 기능 분리**
- **외부 연동 분리** : 네트워크, 메시징, 파일 등 연동 처리 코드 분리
- **조건별 분기는 추상화** : 연속적인 if-else부분이 있다면 추상화 고민

### 주의 : 의도가 잘 드러나는 이름 사용

### 역할 분리와 테스트

- 역할 분리가 잘 되면 테스트도 용이해짐 (전체 기능이 아닌 특정 일부 기능만 테스트 하는 것이 용이해진다.)

### 분리 연습

**분리 연습1**

```java
public class CashClient{
  private SecretKeySpec KeySpec;
  private IvParameterSpec ivSpec;
  
  private Res post(Req req){
    String reqBody = toJson(req);	//JSON 변형 API
    
    //암호화
    Cipher cipher = Cipher.getInstance(DEFAULT_TRANSFORM);
    cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
    String encReqBody = new String(Base64.getEncoder().encode(cipher.doFinal(reqBody)));
    //보낸 결과를 받는다.
    ResponseEntitiy<String> responseEntitiy = restTemplate.postForEntitiy(api, encReqBody, String.class);
    //응답의 body
    String encRespBody = responseEntitiy.getBody();
    //응답의 body를 복호화
    Cipher cipher2 = Cipher.getInstance(DEFAULT_TRANSFORM);
    cipher2.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
    String respBody = new String(cipher.doFinal(Base64.getDecoder().decode(encRespBody)));
    //다시 repository로 바꾸어주는 것
    return jsonToObj(respBody);
  }
}
// 우리는 암호화, 복호화하는 부분을 분리할 수 있다. ( Cryptor 메서드 사용 )

public class Cryptor{
  private SecretKeySpec KeySpec;
  private IvParameterSpec ivSpec;
  
  //암호화 기능
  public String encrypt(String plain){
    Cipher cipher = Cipher.getInstance(DEFAULT_TRANSFORM);
    cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
    return new String(Base64.getEncoder().encode(cipher.doFinal(plain)));
  }
  
  //복호화 기능
  public String decrypt(String encrypted){
    CIpher cipher2 = Cipher.getInstance(DEFAULT_TRANSFORM);
    cipher2.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
    return new String(cipher.doFinal(BAse64.getDecode().decode(encrypted)));
  }
}

public class CashClient{
  private Cryptor cryptor;
  
  private Res post(Req req){
    String reqBody = toJson(req);
    
    String encReqBody = cryptor.encrypt(reqBody);
    
    ResponseEntitiy<STring> responseEntitiy = restTemplate.postForEntity(api, encReqBody, String.class);
    String encRespBody = responseEntitiy.getBody();
    
    String respBody = cryptor.decrypt(encRespBody);
    
    return jsonToObj(respBody);
  }
}



```

**분리 연습2**

```java
public class Movie {
  public static int REGULAR = 0;
  public static int NEW_RELEASE = 1;
  private int priceCode;
  
  public int getPriceCode(){
    return priceCode;
  }
}

public class Rental{
  private Movie movie;
  private int daysRented;
  
  public int getFrequentREnterPoints(){
    if(movie.getPriceCode() == Movie.New_RELEASE && daysRented > 1) //추상화	필요
      return 2;
    else
      return 1;
  }
}

// ...
public abstract class Movie{
  public abstract int getFrequentRenterPoints(int daysRented);
  //...
}

public class NewReleaseMovie extends Movie{
  public int getFrequentRenterPoints(int daysRented){
    return daysRented > 1 ? return 2 : 1;
  }
}

public class RegularMovie extends Movie{
  public int getFrequentRenterPoints(int daysRented){
    return 1;
  }
}

public class Rental{
  private Movie movie;
  private int daysRented;
  
  public int getFrequentRenterPoints(){
    return movie.getFrequendRenterPoints(daysRented);
  }
  //...
}
```

---

## 의존과 DI

### 의존

- **기능 구현을 위해 다른 구성 요소를 사용하는 것**
   - 의존의 예 : 객체 생성, 메서드 호출, 데이터 사용
- **의존은 변경이 전파될 가능성을 의미**
   - 의존하는 대상이 바뀌면 바뀔 가능성이 높아짐

### 순환 의존

- 순환 의존 -> 변경 연쇄 전파 가능성
   - 클래스, 패키지, 모듈 등 모든 수준에서 순환 의존 없도록 한다.

### 의존 대상 많을 때

- 기능 별로 분리를 고려해야한다.
- 몇 가지 의존 대상을 단일 기능으로 묶어서 생각해보면 의존 대상을 줄일 수 있음

### 의존 대상 객체를 직접 생성하면?

- 생성 클래스가 바뀌면 의존하는 코드도 바뀜
   - 추상화에서 언급
- 의존 대상 객체를 직접 생성하지 않는 방법
   - 팩토리, 빌더
   - 의존 주입(Dependency Injection)
   - 서비스 로케이터(Service Locator)

### 의존 주입(Dependency Injection)

- 외부에서 의존 객체를 주입

   - 생성자나 메서드를 이용해서 주입

      ```java
      public class ScheduleService{
        private UserRepository repository;
        private Calculator cal;
        
        public ScheduleService(UserRepository repository){
          this.repository = repository;
        }
        
        public void setCalculator(Calculator cal){
          this.cal = cal;
        }
      }
      
      //초기화 코드
      UserRepository userRepo = new DbUserRepository();
      Calculator cal = new Calculator();
      
      ScheduleService schSvc = new ScheduleService(userRepo);
      schSvc.setCalculator(cal);
      ```

### 조립기(Assembler)

- 조립기가 객체 생성, 의존 주입을 처리

   - 예: 스프링 프레임워크

   ```java
   @Configuration
   public class Config{
     @Bean
     public ScheduleService scheuleSvc(){
       ScheduleService svc = new ScheduleService(repo());
       svc.setCalculator(expCal());
       return svc;
     }
     
     @Bean
     public UserRepository repo() {
       //...
     }
     
    	@Bean
     public Calculator expCal() {
       //...
     }
   }
   
   // 초기화
   ctx = new AnnotationConfigApplicationContenxt(Config.class);	//스프링 제공 조립기
   
   // 사용할 객체 구함
   ScheduleService svc = ctx.getBean(ScheduleService.class);
   
   //사용
   svc.getSchedule(..);
   ```

### 의존성 주입의 장점

1. **상위 타입을 사용할 경우 의존 대상이 바뀌면 조립기(설정)만 변경하면 됨**
2. **의존하는 객체 없이 대역 객체를 사용해서 테스트 가능**
   - 대역 객체를 사용하면 초기화가 가능하기 때문에 다양한 경우의 수 테스트 가능

### DI를 습관처럼 사용하기

- **의존 객체는 주입받고록 코드 작성하는 습관 기르기**

### 다음 학습 추천

- 복습
- TDD (개발 속도, 좋은 설계 가능성 높여줌)
- 함수형 프로그래밍 기초(비용을 낮춰주는 다른 방법)
- 각 패러다임의 설계 패턴(지식/지혜 재사용)
- UML(도식화)

---

## DIP 

### 고수준 모듈, 저수준 모듈

- **고수준 모듈**
   - 의미 있는 단일 기능을 제공
   - 상위 수준의 정책 구현
- **저수준 모듈**
   - 고수준 모듈의 기능을 구현하기 위해 필요한 하위 기능의 실제 구현

### 고수준이 저수준에 직접 의존하면

- 저수준 모듈 변경 -> 고수준 모듈에 영향

### DIP(Dependency Inversion Principle)

- **의존 역전 원칙**
   - 고수준 모듈은 저수준 모듈의 구현에 의존하면 안 됨
   - 저수준 모듈이 고수준 모듈에서 정의한 추상타입에 의존해야 함

### 고수준 관점에서 추상화

- **고수준 입장에서 저수준 모듈을 추상화**
   - 구현 입장에서 추상화하지 말 것

### DIP는 유연함을 높임

- 고수준 모듈의 변경을 최소화하면서 저수준 모듈의 변경 유연함을 높임

### 부단한 추상화 노력 필요

- 처음부터 바로 좋은 설계가 나오지는 않음
   - 요구사항/업무 이해가 높아지면서 저수준 모듈을 인지하고 상위 수준 관점에서 저수준 모듈에 대한 추상화 시도

### 연습

- 상품 상세 정보와 추천 상품 목록 제공 기능
   - 상품 번호를 이용해서 상품 DB에서 상세 정보를 구함
   - Daara API를 이용해서 추천 상품 5개 구함
   - 추천 상품이 5개 미만이면 같은 분류에 속한 상품 중 최근 한달 판매가 많은 상품을 ERP에서 구해서 5개를 채움
- 고수준
   - 상품 번호로 상품 상세 정보 구함
   - 추천 상품 5개 구함
   - 인기 상품 구함
- 저수준
   - DB에서 상세 정보 구함
   - Daara API에서 상품 5개 구함
   - 같은 분류에 속한 상품에서 최근 한달 판매가 많은 상품 ERP에서 구함



































