module Jeyson
  class TestHelper
    def path_to_jar
      "/Users/dawn/Documents/projects/wiremock/wiremock-standalone-2.1.12"
    end

    def root_dir
      "/Users/dawn/Documents/projects/wiremock/"
    end

    def port
      5132
    end

    def stub_root_url()
      "http://localhost:#{port}"
    end
  end
end
