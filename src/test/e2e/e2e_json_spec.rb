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
      expected  = {"message" => "hello"}
      actual    = @client.get("/hello")
      expect(actual).to eq(expected)
    end

    it "Should parse expressoins in json templeates" do
      expected =  {"body"=>{"message"=>"hello","request"=>["one"," two"," three"],"headers"=>["application/json"],"float"=>1.1,"sum"=>1,"nil"=>nil,"boolean"=>false,"list"=>["one","two","three","four","five"],"repeater"=>[{"id"=>"1-one"},{"id"=>"2-two"},{"id"=>"3-three"}]}}
      actual    = @client.put("/expressions", {"names" => "one, two, three"})
      expect(actual).to eq(expected)
    end

    it "Should set session values in before block" do
      session = @client.get("/get-session")
      expect(session["user"]["id"]).to eq(nil)

      @client.put("/set-session", {"userId" => "session-user-id"})
      session = @client.get("/get-session")
      expect(session["user"]["id"]).to eq("session-user-id")


      @client.put("/set-session", {"userId" => "updated-session-user-id"})
      session = @client.get("/get-session")
      expect(session["user"]["id"]).to eq("updated-session-user-id")
    end

  end
end
