package me.meow.features.modules.misc;

import me.meow.features.modules.Module;
import me.meow.features.modules.client.Debug;
import me.meow.features.settings.Setting;
import me.meow.util.models.Timer;

import java.text.DecimalFormat;
import java.util.Random;

public class Spammer extends Module {
    public static enum Mode{
        Hack, Meow, Ru, Arab, One
    }
    private final Timer delayTimer = new Timer();
    public Setting<Mode> mode = this.register(new Setting<>("Mode", Mode.Meow));
    public Setting<Double> delay = this.register(new Setting<>("Delay", 10d, 1d, 30d));
    public Spammer(){
        super("Spammer","",Category.MISC,true,false,false);
    }
    @Override
    public void onUpdate() {
        if (mode.getValue() == Mode.Hack && delayTimer.passedS(delay.getValue())) {
            mc.player.networkHandler.sendChatMessage(getHackMessage());
            delayTimer.reset();
        }
        if (mode.getValue() == Mode.Meow && delayTimer.passedS(delay.getValue())) {
            mc.player.networkHandler.sendChatMessage(getMeowMessage());
            delayTimer.reset();
        }
        if (mode.getValue() == Mode.Ru && delayTimer.passedS(delay.getValue())) {
            mc.player.networkHandler.sendChatMessage(getRuMessage());
            delayTimer.reset();
        }
        if (mode.getValue() == Mode.Arab && delayTimer.passedS(delay.getValue())) {
            mc.player.networkHandler.sendChatMessage(getArabMessage());
            delayTimer.reset();
        }
        if (mode.getValue() == Mode.One && delayTimer.passedS(delay.getValue())) {
            mc.player.networkHandler.sendChatMessage(getOneMessage());
            delayTimer.reset();
        }
    }
    private String getHackMessage(){
        String[] walkMessage = {
                "Meowclient on top",
                "Meowclient zapen",
                "Meowclient very good",
                "I will fuck you with my meowclient!",
                "Your mother is buying my meowclient!",
                ">:3"
        };

        return walkMessage[new Random().nextInt(walkMessage.length)];
    }
    private String getMeowMessage(){
        String[] walkMessage = {
               "There are over 500 million domestic cats in the world",
               "В каждом кошачьем ухе находится более чем 30 мускулов",
               "В среднем, коты спят по 16-18 часов в день, что составляет более 70% кошачьей жизни",
               "Кот Стаббс был мэром местности Талкитна (штат Аляска, США) в течении 15 лет",
               "Кошки не чувствуют вкуса сладкого",
                "Cat owners reduce their risk of stroke and heart attack by an average of one third",
               " Самая богатая кошка в мире владеет состоянием в 13 миллионов долларов. Эти деньги оставил ей в наследство умерший владелец.",
                "Коты часто страдают непереносимостью лактозы. Не давайте им молоко ",
       " В 60-х, в ЦРУ хотели превратить кота в настоящего шпиона. Чудом кот пережил операцию по вживлению микрофона в ухо и радиопередатчика в череп. Но на первой же миссии кот был сбит такси",
                "Коты могут издавать более чем 100 различных звуков, тогда как собаки — всего лишь около 10",
        "У кошек и людей за управление эмоциональным состоянием отвечают одинаковые отделы головного мозга",
               " В кошачьей памяти в 1000 раз больше места, чем в Iphone",
                "Contrary to popular belief, the cat was not a sacred animal in ancient Egypt. However, killing these felines was punishable by law. Cats controlled and did not allow the rat population to grow",
                "У будинку письменника Ернеста Хемінгуея у місті Кі-Уест (штат Флорида, США) проживало 45 котів"
        };

        return walkMessage[new Random().nextInt(walkMessage.length)];
    }
    private String getOneMessage(){
        String[] walkMessage = {
                "1",
                "One",
                "I SUCK YOU FATHER!"
        };

        return walkMessage[new Random().nextInt(walkMessage.length)];
    }
    private String getRuMessage() {
        String[] walkMessage = {
                "ты понимаешь что я на чердаке твою бабулю повесил своим хуем",
                "ты понимаешь что я об лобок твоей маамшки своим хуем орехи колю",
                "мать твоя стонет под скрипы гитары от моего хуя",
                "мне твою щеку что ли потереть как люстру?",
                "слышь, твой рот мне же говорил что ты проститутка влиятельной колхозницы",
                "почему ты прячешь мой хуй у себя во рту, любишь чтоле?",
                "когда твоя мамаша моим хуем играет в настольные игры, ты зачем мне хуй сосешь?",
                "я могу ебать твою мать на неведомом отстрове",
                "давай из твоего лица вырежим кусочек кожи и сделаем из него призерватив?",
                "ты заебал мне всасывать, ядобью твой рот до конца ты будешь умолять что бы я тад тебе сосать",
                "твоя мамаша когда сдохла хули ты мне в хуй ныл?",
                "ебал твою мать в жопу пока ты мне хуй сосал",
                "сын собаки слышь долбанутая иди отсюда и мамку свою прихвати",
                "когда твой папа стал раком русский охотник выстрелил ему в анал с ружья приняв его за лося",
                "ты понимаешь что я выбил своим хуем гланды твоей мамашки?",
                "слыш) бревно ты ебаное) мамашу твою на сеновале ебал",
                "я твою мамашу пас своим хуем на майдане",
                "ты понимаешь что я щас своим хуем через пиздак твоей мамашки буду торговые пути проводить",
                "я пиздой твоей матери вкрутил лампочку",
                "я ща через свой хуй пущу электро заряд тебе в мозг",
                "пидор огнедыщащий иди сюда я тебя ебать буду",
                "нахуй ты проводил тест драйв на моем хуе",
                "твоя мать на моем хуе ездит покупать моему хую украшения",
                "твоя мать зачем хвастается моим хуем перед подружками",
                "твоя мать вместо подушки ставит мой хуй себе под голову и так спит",
                "твоя мать моим хуем убила сталина",
                "твоя мать моим хуем умеет останавливать пули",
                "твоя мать когда играет в бадминтон вместо ракетки использует мой хуй",
                "твоя мать когда выпила мою сперму у неё крыша поехала и она начала танцевать русскую чечетку",
                "моя сперма такая дрогоценая что твоя мать её наливает в баночки и начинает ей молится",
                "твоя мать мою сперму пила как водку",
                "твоя мать увидела на витрине мой хуй и каждый раз когда отец твоей матери не давал она брала мой хуй и ебала себя",
                "давай я твою мать поебу на касписком море и буду подпевать песню каспийский груз",
                "твоя мать стирала тебе одежду моей спермой",
                "твоя мать построила одежный завод в виде моего хуя",
                "зачем твоя мать на моем хуе поставила светильник ?",
                "ты когда просил борщ со сметаной твоя мать приносила борщ с моей спермой и ты даже замечал",
                "я на пизде твоей матери устраивал скачки на конях",
                "я тебе ша глаз протикну своим хуем как шампур шашлык",
                "твоя мать когда готовит торт всегда добавляет 10 милиграм моей спермы",
                "мой хуй посадил яблоню на пизде твоей матеи",
                "мой хуй построил кондитерскую фабрику"
        };
        return walkMessage[new Random().nextInt(walkMessage.length)];

    }
    private String getArabMessage() {
        String[] walkMessage = {
                "هل تفهم أنني علقت جدتك في العلية بقضيبي",
                "هل تفهم أنني تكسير المكسرات على العانة أمك مع ديك بلدي",
                "أمك تشتكي تحت صرير الغيتار من قضيبي",
                "يجب أن فرك خدك مثل الثريا?",
                "مهلا ، فمك هو نفسه بالنسبة لي قال أنك عاهرة لمزارع جماعي مؤثر",
                "لماذا إخفاء ديك بلدي في فمك, هل تحب شيئا?",
                "عندما يلعب أمك ألعاب الطاولة مع ديك بلدي, لماذا تمتص ديك بلدي?",
                "يمكنني مضاجعة والدتك في شبه جزيرة مجهولة",
                "دعونا قطع من وجهك قطعة من الجلد وجعل الواقي الذكري للخروج منه?",
                "لقد مارست الجنس معي ، سأسمم فمك حتى النهاية، سوف تتوسل إلي أن أمتصك",
                "أمك عندما ماتت, هل يمارس الجنس معي في ديك?",
                "مارس الجنس أمك في الحمار بينما كنت امتص ديك بلدي",
                "ابن كلب ، أيها الوغد ، اخرج من هنا وأمسك بأمك",
                "عندما أصيب والدك بالسرطان ، أطلق عليه صياد روسي النار في الشرج بمسدس ، ظنا منه أنه إلك",
                "هل تفهم أنني طرقت اللوزتين والدتك مع ديك بلدي?",
                "هل تسمع) أنت سخيف سجل) مارس الجنس أمك في هايلوفت",
                "أمرر أمك بقضيبي على الميدان",
                "أنت تفهم أنه الآن سأقوم بإجراء طرق التجارة مع قضيبي من خلال مهبل والدتك،",
                "أنا ثمل المصباح الكهربائي في العضو التناسلي النسوي والدتك،",
                "سأضع شحنة كهربائية في دماغك من خلال قضيبي",
                "شاذ يأكل النار ، تعال إلى هنا سأضاجعك،",
                "اللعنة عليك ، لقد أجريت تجربة قيادة على اللعنة",
                "أمك تركب قضيبي لشراء مجوهرات لقضيبي",
                "لماذا أمك التباهي ديك بلدي أمام صديقاتها",
                "أمك تضع قضيبي تحت رأسها بدلا من الوسادة وتنام هكذا",
                "أمك قتلت ستالين بقضيبي",
                "أمك تعرف كيف توقف الرصاص بقضيبي",
                "أمك عندما تلعب كرة الريشة تستخدم قضيبي بدلا من المضرب",
                "أمك عندما شربت حيواني المنوي ، أصيبت بالجنون وبدأت ترقص رقص الصنبور الروسي",
                "حيواناتي المنوية ثمينة جدا لدرجة أن والدتك تصبها في الجرار وتبدأ في الصلاة لها",
                "أمك شربت الحيوانات المنوية مثل الفودكا",
                "رأت والدتك قضيبي في النافذة وفي كل مرة لم يسمح لها والد والدتك ، أخذت قضيبي ومارست الجنس مع نفسها",
                "اسمحوا لي أن يمارس الجنس مع والدتك على بحر قزوين والغناء على طول لأغنية بحر قزوين البضائع",
                "أمك غسلت ملابسك بحيواني المنوية",
                "أمك بنت مصنع ملابس على شكل قضيبي",
                "لماذا وضعت والدتك مصباح على ديك بلدي?",
                "عندما طلبت البرش مع القشدة الحامضة ، أحضرت والدتك البرش مع الحيوانات المنوية الخاصة بي وحتى لاحظت",
                "لقد رتبت سباقات الخيول على كس والدتك",
                "أنا شا لك سوف فرك عيني مع ديك بلدي مثل سيخ الشواء",
                "تضيف والدتك دائما 10 ملليغرام من الحيوانات المنوية عندما تطبخ كعكة",
                "ديك بلدي زرعت شجرة تفاح على كس والدتك",
                "ديك بلدي بنى مصنع الحلويات",
                "﷽﷽ ﷽﷽﷽ ﷽﷽﷽ ﷽﷽﷽ ﷽﷽﷽ ﷽﷽﷽ ﷽﷽﷽ ﷽﷽﷽ ﷽﷽﷽﷽ ﷽﷽﷽ ﷽﷽﷽ ﷽﷽﷽ ﷽﷽"
        };
        return walkMessage[new Random().nextInt(walkMessage.length)];

    }


}
