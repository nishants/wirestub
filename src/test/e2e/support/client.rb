require 'http' # URI is required by Net::HTTP by default
require 'json' # URI is required by Net::HTTP by default

module Jeyson
  class Client
    def initialize(root, port, default_headers = {'Content-Type' => "application/json"})
      @root     = root
      @port     = port
      @headers  = default_headers
    end

    def get(request_url, request_headers = {})
      JSON.parse(HTTP.headers(headers(request_headers)).get(url(request_url)).body.to_s)
    end

   def put(request_url, body, request_headers = {})
     JSON.parse(HTTP.headers(headers(request_headers)).put(url(request_url), :body => body.to_json).body.to_s)
    end

    def headers(request_headers)
      @headers.merge(request_headers)
    end

    def url(url)
      "#{@root}#{url}"
    end

  end
end