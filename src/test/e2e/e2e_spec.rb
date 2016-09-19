require './src/test/e2e/support/helper'
require './src/test/e2e/support/client'

RSpec.describe "jeyson-java" do

  describe "e2e" do

    before(:all) do
      @helper = Jeyson::TestHelper.new
      @client = Jeyson::Client.new(@helper.stub_root_url)
      @helper.start_server
    end

    after(:all) do
      @helper.stop_server
    end

    it "Should serve a plain json" do
      expect(@client.get("/hello")).to  eq(@helper.read("expected/hello.json"))
      expect(@client.get("/xml/hello")).to eq(@helper.read("expected/hello.xml"))
    end

    it "should support request param query" do
      expect(@client.get("/query?time=now&message=hi")).to eq(@helper.read("expected/query.json"))
    end

    it "Should parse expressions in json templeates" do
      expect(@client.put("/expressions", {"names" => "one, two, three"})).to eq(@helper.read("expected/expressions.json"))
    end

    it "Should set session values in before block" do
      session = JSON.parse(@client.get("/get-session"))
      expect(session["user"]["id"]).to eq(nil)

      @client.put("/set-session", {"userId" => "session-user-id"})
      session = JSON.parse(@client.get("/get-session"))
      expect(session["user"]["id"]).to eq("session-user-id")


      @client.put("/set-session", {"userId" => "updated-session-user-id"})
      session =JSON.parse(@client.get("/get-session"))
      expect(session["user"]["id"]).to eq("updated-session-user-id")
    end

  end
end
