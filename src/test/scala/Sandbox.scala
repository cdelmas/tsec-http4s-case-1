import munit.FunSuite

class Sandbox extends FunSuite {

  //private val sk = "sk_test_FHZkbREMjAHweyzhlgwbDN1k"
  /*
  test("connect to stripe") {
    Stripe.apiKey = sk

    val uuid = Random.alphanumeric.take(8).mkString("")
    val prog = IO {
      val customer = Customer.create(
        CustomerCreateParams
          .builder()
          .setEmail(s"test${uuid}@example.com")
          .setDescription("Example Description")
          .build()
      )
      println(customer)
    } handleErrorWith { e =>
      IO(e.printStackTrace())
    }

    prog.unsafeRunSync()
  }
   */
  /*
  test("attach a card to a customer") {
    val prog = IO {
      val customerId = "cus_HrzpwzqNeXdnWs" // previously created
      val paymentMethod = PaymentMethod.create(
        PaymentMethodCreateParams
          .builder()
          .setCard(
            PaymentMethodCreateParams.CardDetails
              .builder()
              .setNumber("4242424242424242")
              .setExpMonth(12L)
              .setExpYear(2021L)
              .setCvc("666")
              .build()
          )
          //.setCustomer(customerId)
          .build()
      )
      paymentMethod.attach(PaymentMethodAttachParams.builder().setCustomer(customerId).build())
      println(paymentMethod)
    } handleErrorWith { e =>
      IO(e.printStackTrace())
    }

    prog.unsafeRunSync()
  }
   */
  //test("Create an invoice for a customer") {}
}
