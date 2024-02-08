# KURUMLAR VE PROJE TEKNOLOJILERI

## Docker üzerinden postgreSQL kurulumu

    Uyhulamamızda Auth Service üzeribde kullanıcı oturum açma işlemlerini ve kaytılarını ilişkisel bir veritabanında
    tutuyoruz. Veritabanı olarak PostgresSWL kullanıyoruz. postgresSQL i docker üzerinde çalıştırmak için aşağıdaki kodu 
    kullanabiliriz

'''
    docker run --name postgreSQL -e POSTGRES_PASSWORD=root -p 5432:5432 -d postgres
'''

## Docker üzerinde mongoDB çalştırmak

    MongoDB kurulumu yaparken yetkili kullanıcı bilgilerinin girilmesi gereklidir. Bu bilgileri imajların
    Ortam Değişkenlerine atayarak yapabiliyorsunuz. Docker bu tarz biilgileri ekleyebilmeniz için size 
    ek parametreler sunmaktadır
    Ek Bilgi:
    docker üzerinde bir imaj eklemek istiyorsak -> docler pull [IMAGE_NAME]
    doker üzerinde bir imajı çalıştırmak istiyorsak -> docker run [IMAGE_NAME]
    Burada önemli bir nokta vardır. docker run eğer ortamda ilgili imaj yok ise önce imajı indirir sonra çalıştırır
    yani docker run yapmak için önce imajı pull etmeniz gerek yoktur

'''
    docker run --name java13MongoDB -d -e "MONGO_INITDB_ROOT_USERNAME=admin" -e "MONGO_INITDB_ROOT_PASSWORD=root" -p 27017:27017 mongo:jammy
'''
    
    MongoDB yi yönetebilmek için bir araça ihtiyacımız var. Bu aracın adı. MongoDB Compos tool. bu aracı indirip
    kurmanız gereklidir. Adres: https://www.mongodb.com/try/download/compass

    Compas kurulumu bittikten sonra, açılan yeni pencerede "New Connetion +" butonuns tıklıyorsunuz. ekranın ortasında
    "> Advanced connection options" butonuna tıklayarak detaylı bağlantu ayarlarını yapıyoruz.
    1- açılan ekranda "Host" kısmına veritabanınızın ip adresinin ve port numarasını giriyorsunuz. yerel bilgisayarınız
    için kullanılacak ise ya da docker desktop üzerinde ise (localhost:27017) şeklinde yazıyoruz.
    2- Authentication kısmına geçiş yaparak kurulum sırasında girdiğiniz kullanıcı adı ve şifre yazıyorsunuz. Docker
    run komutu ile çalıştırdı iseniz -e ile giriş yaptığınız bilgileri yazınız. (admin - root)

    NOT: MongoDB yi ilk kurulumları ve kullanımları için admin kullanıcı ile işlemleri yapabilirsiniz. Ancak 
    veritabanlarını yönetmek ve işlemek için kullanmayınız. Her DB için ayrı kullanici ve yetkiler oluşturunuz
    root kullanıcı ve şifreler sadece ilk kurulum için kullanılmalı ve tekrar kulllanılmamalıdır. Sadece gerekli
    olduğu durumlarda müdahele için kullanınız
    Gerekli dökümantosyanalara: hhtps://www.mongodb.com/docs/manual/ ulaşabilirsiniz

    Yetkilendirme işlemi
    1- MONGOSH a tıklayarak açıyoruz
    2- açılan kısmıda test> şeklinde bir yer göreceksiniz, öncelikle test DB den kendi DB nize geçmek için
    use [DB_ADI] yazıp enter a basınız.
    Örn:
    use UserProfile
    switches to db UserProfile
    UserProfile> şeklinde bir görüntü elde edeceksiniz.
    3- burada kullanıcı oluşturmak için gerekli komutları giriyoruz
    db.createUser({
    user: "bilgeUser",
    pwd: "bilgeUser",
    roles: ["readWrite","dbAdmin"]
    })
/*
    db.createUser({user: "bilgeUser",pwd: "bilgeUser",roles: ["readWrite","dbAdmin"]})
*/
