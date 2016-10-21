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
      expect(JSON.parse @client.get("/hello")).to  eq(JSON.parse @helper.read("expected/hello.json"))
    end

    it "Should serve a plain xml" do
      expect(@client.get("/xml/hello")).to eq(@helper.read("expected/xml/hello.xml"))
    end

    it "should support request param query" do
      expect(JSON.parse @client.get("/query?time=now&message=hi")).to eq(JSON.parse @helper.read("expected/query.json"))
      expect(@client.get("/xml/query?time=now&message=hi")).to eq(@helper.read("expected/xml/query.xml"))
    end

    it "Should parse expressions in json templeates" do
      actual    = JSON.parse(@client.put("/expressions", {"names" => "one, two, three"}))
      expected  =  JSON.parse(@helper.read("expected/expressions.json"))

      expect(actual).to eq(expected)
    end

    it "Should set session values in before block" do
      session = JSON.parse(@client.get("/get-session"))
      expect(session["user"]["id"]).to eq(nil)

      @client.put("/set-session", {"userId" => "session-user-id"})
      session = JSON.parse(@client.get("/get-session"))
      expect(session["user"]["id"]).to eq("session-user-id")


      @client.put("/set-session", {"userId" => "updated-session-user-id"})
      session = JSON.parse(@client.get("/get-session"))
      expect(session["user"]["id"]).to eq("updated-session-user-id")
    end

  end
end
