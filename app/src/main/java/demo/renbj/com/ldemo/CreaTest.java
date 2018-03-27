package demo.renbj.com.ldemo;

import android.util.Log;
import io.reactivex.*;
import io.reactivex.disposables.Disposable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class CreaTest {

    private static final String TAG="creat";

   public void create(){
       Observable.create(new ObservableOnSubscribe<Integer>(){

           //ObservableOnSubscribe它的作用相当于一个计划表，当 Observable被订阅的时候，ObservableOnSubscribe的subscribe()方法会自动被调用，事件序列就会依照设定依次触发
           @Override
           public void subscribe(ObservableEmitter<Integer> e) throws Exception {

               e.onNext(1);
               e.onNext(3);
               e.onNext(5);

               e.onComplete();
           }
       }).subscribe(new Observer<Integer>() {
           @Override
           public void onSubscribe(Disposable d) {
               Log.e(TAG,"onSubscribe = = = ");
           }

           @Override
           public void onNext(Integer integer) {
                Log.e(TAG,"onNext :"+integer);
           }

           @Override
           public void onError(Throwable e) {
               Log.e(TAG,"onError :"+e.getLocalizedMessage());
           }

           @Override
           public void onComplete() {
               Log.e(TAG,"onComplete ......");
           }
       });

//       Observable.just(T1,....T10); 直接创建并且发送事件，最多只能发送10个参数

//       Integer[] items = {0,1,2,3,4};
//       Observable.fromArray(items);

//       List<Integer> list=new ArrayList<>();
//       list.add(1);
//       list.add(2);
//       Observable.fromIterable(list);

//       该方法创建的被观察者对象发送事件的特点：仅发送Complete事件，直接通知完成
//               Observable observable1=Observable.empty();
//       即观察者接收后会直接调用onCompleted（）

//        该方法创建的被观察者对象发送事件的特点：仅发送Error事件，直接通知异常
//        可自定义异常
//               Observable observable2=Observable.error(new RuntimeException())
//        即观察者接收后会直接调用onError（）

//        该方法创建的被观察者对象发送事件的特点：不发送任何事件
//               Observable observable3=Observable.never();
//        即观察者接收后什么都不调用

   }

   Integer i =10;
   public void defer(){

       Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
           @Override
           public ObservableSource<? extends Integer> call() throws Exception {
               return Observable.just(i);
           }
       });

       i=15;
       observable.subscribe(new Observer<Integer>() {
           @Override
           public void onSubscribe(Disposable d) {
               Log.d(TAG, "开始采用subscribe连接");
           }

           @Override
           public void onNext(Integer integer) {
               Log.d(TAG, "接收到的整数是"+ integer  );
           }

           @Override
           public void onError(Throwable e) {

           }

           @Override
           public void onComplete() {
               Log.d(TAG, "对Complete事件作出响应");
           }
       });

   }

}
