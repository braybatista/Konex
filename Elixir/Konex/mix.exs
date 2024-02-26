defmodule ReactiveCommons.MixProject do
  use Mix.Project

  @version "1.0.0"

  def project do
    [
      app: :Konex,
      version: @version,
      elixir: "~> 1.13",
      deps: deps(),
      description: description(),
      package: package(),
      source_url: "https://github.com/braybatista/Konex",
      xref: [exclude: [:crypto]],
      name: "Konex",
    ]
  end

  defp hello(_) do
    Mix.shell().info("Hello world")
  end

  defp description() do
    "Domain driven async abstractions like Domain Event Bus, Event subscriptions/emit, Async Command handling and Async Req/Reply."
  end

  # Run "mix help deps" to learn about dependencies.
  defp deps do
    [
      {:decimal, "~> 1.0"},
      {:ex_doc, "~> 0.14", only: :dev, runtime: false}
    ]
  end

  defp package() do
    [
      # This option is only needed when you don't want to use the OTP application name
      files: ["mix.exs", ".formatter.exs"],
      maintainers: ["Brayan Batista Zuniga"],
      licenses: ["Apache-2.0"],
      links: %{"GitHub" => "https://github.com/braybatista/Konex"}
    ]
  end
end