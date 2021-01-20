package matching;

import matching.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired MyPageRepository MyPageRepository;
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverVisitCanceled_(@Payload VisitCanceled visitCanceled){

        if(visitCanceled.isMe()){
            System.out.println("##### listener  : " + visitCanceled.toJson());

//            MyPage mypage = new MyPage();
            MyPageRepository.findById(visitCanceled.getMatchId()).ifPresent(MyPage ->{
                System.out.println("##### wheneverVisitCanceled_MyPageRepository.findById : exist" );
                MyPage.setStatus(visitCanceled.getEventType());
                MyPageRepository.save(MyPage);
            });
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverVisitAssigned_(@Payload VisitAssigned visitAssigned){

        if(visitAssigned.isMe()){
            System.out.println("##### listener wheneverVisitAssigned  : " + visitAssigned.toJson());

            MyPageRepository.findById(visitAssigned.getMatchId()).ifPresent(MyPage ->{
                System.out.println("##### wheneverVisitAssigned_MyPageRepository.findById : exist" );

                MyPage.setStatus(visitAssigned.getEventType()); //상태값은 모두 이벤트타입으로 셋팅함
                MyPage.setTeacher(visitAssigned.getTeacher());
                MyPage.setVisitDate(visitAssigned.getVisitDate());
                MyPageRepository.save(MyPage);
            });

        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentApproved_(@Payload PaymentApproved paymentApproved){

        if(paymentApproved.isMe()){
            System.out.println("##### wheneverPaymentApproved_listener  : " + paymentApproved.toJson());

            System.out.println("##### paymentApproved.getMatchId() : "+paymentApproved.getMatchId() );

            /**
            MyPageRepository.findById(paymentApproved.getMatchId()).ifPresent(MyPage ->{
                System.out.println("##### wheneverPaymentApproved_MyPageRepository.findById : exist" );

                MyPage.setStatus(paymentApproved.getEventType()); //상태값은 모두 이벤트타입으로 셋팅함
                MyPageRepository.save(MyPage);
            });
             **/

            MyPage mypage = new MyPage();
            mypage.setId(paymentApproved.getMatchId());
            mypage.setPrice(paymentApproved.getPrice());
            mypage.setStatus(paymentApproved.getEventType());

            MyPageRepository.save(mypage);
        }


    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentCanceled_(@Payload PaymentCanceled paymentCanceled){

        if(paymentCanceled.isMe()){
            System.out.println("##### listener  : " + paymentCanceled.toJson());


            MyPageRepository.findById(paymentCanceled.getMatchId()).ifPresent(MyPage ->{
                System.out.println("##### wheneverPaymentCanceled_MyPageRepository.findById : exist" );

                MyPage.setStatus(paymentCanceled.getEventType()); //상태값은 모두 이벤트타입으로 셋팅함
                MyPageRepository.save(MyPage);
            });
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverMatchCanceled_(@Payload MatchCanceled matchCanceled){

        if(matchCanceled.isMe()){
            System.out.println("##### listener  : " + matchCanceled.toJson());

            MyPageRepository.findById(matchCanceled.getId()).ifPresent(MyPage ->{
                System.out.println("##### wheneverMatchCanceled_MyPageRepository.findById : exist" );

                MyPage.setStatus(matchCanceled.getEventType()); //상태값은 모두 이벤트타입으로 셋팅함
                MyPageRepository.save(MyPage);
            });

        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverMatchRequested_(@Payload MatchRequested matchRequested){

        if(matchRequested.isMe()){
            System.out.println("##### wheneverMatchRequested_listener  : " + matchRequested.toJson());

            /**
            MyPage mypage = new MyPage();
            mypage.setId(matchRequested.getId());
            mypage.setPrice(matchRequested.getPrice());
            mypage.setStatus(matchRequested.getEventType());
            mypage.setStudent(matchRequested.getStudent());
            MyPageRepository.save(mypage);
             **/

            MyPageRepository.findById(matchRequested.getId()).ifPresent(MyPage ->{
                System.out.println("##### wheneverPaymentApproved_MyPageRepository.findById : exist" );

                MyPage.setId(matchRequested.getId());
                MyPage.setStudent(matchRequested.getStudent());
//                MyPage.setStatus(paymentApproved.getEventType()); //상태값은 모두 이벤트타입으로 셋팅함
                MyPageRepository.save(MyPage);
            });
        }
    }

}
