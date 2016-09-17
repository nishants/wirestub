require './src/test/e2e/support/helper'
require './src/test/e2e/support/client'

RSpec.describe "jeyson-java" do

  describe "e2e" do

    before(:all) do
      @helper = Jeyson::TestHelper.new
      @client = Jeyson::Client.new(@helper.stub_root_url, @helper.port)
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

    # it "Should parse expressoins in json templeates" do
    #   expected  = {"message" => "hello"}
    #   actual    = @client.put("/expressions", {"names" => "one, two, three"})
    #   expect(actual).to eq(expected)
    # end

  end
end
