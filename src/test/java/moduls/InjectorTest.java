package moduls;

import org.junit.jupiter.api.Test;

class InjectorTest {

    @Test
    void inject() {
        Injector injector=new Injector();
        Bean bean=new Injector().inject(new Bean());
        bean.foo();
        Container container=new Injector().inject(new Container());
        container.foo();

    }
}