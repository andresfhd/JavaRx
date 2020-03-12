import rx.Observable;
import rx.Single;
import rx.Subscriber;

public class JavaRx {

    public static void main(String[] args) {
        //executeBasicObservable();
        //sayHello();
        //createFilterExample();
        //creatReduceExample();
        //creatZipExample();
        createSingleExample();
    }

    private static void executeBasicObservable() {
        Observable<Integer> integerObservable = Observable.just(1, 2, 3);

        Subscriber<Integer> mSubscriber = new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                // called when all objects are emitted
                System.out.println("onCompleted called!");
            }

            @Override
            public void onError(Throwable throwable) {
                // called when an error occurs during emitting objects
                System.out.println("onError called!");
            }

            @Override
            public void onNext(Integer integer) {
                // called for each object that is emitted
                System.out.println("onNext called with: " + integer);
            }
        };
        integerObservable.subscribe(mSubscriber);
    }

    private static void sayHello() {
        try {
            Observable.just(sayHelloWithDelay()) // create new observable
                    .map(String::toUpperCase)
                    .subscribe(System.out::println);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static String sayHelloWithDelay() {
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            System.out.println(e);
        }
        return "Hello World";
    }

    private static void createFilterExample() {
        try {
            Observable.range(0, 100)
                    .take(10)
                    .filter(value ->
                            value % 2 == 1
                    ).map(value -> Math.sqrt(value))
                    .subscribe(System.out::println);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void creatReduceExample() {
        try {
            Observable<String> stringObservable = Observable.just(" Android or ", "iOS?");
            stringObservable.startWith("Which one do you prefer:").reduce((x, y) -> x + y)
                    .subscribe(System.out::println);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void creatZipExample() {
        try {
            Observable<Integer> stringObservable1 = Observable.just(1, 5, 9);
            Observable<Integer> stringObservable2 = Observable.just(1, 3, 7);

            Observable.zip(
                    stringObservable1,
                    stringObservable2,
                    (s1, s2) -> s1 + s2)
                    .subscribe(System.out::println);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void createSingleExample() {
        try {
            Single<String> testSingle = Single.just("Hello World");
            testSingle
                    .map(value -> value.toLowerCase())
                    .subscribe(System.out::println);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
